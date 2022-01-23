package com.netatmo.ylu.gofoot.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.netatmo.ylu.gofoot.model.Player
import com.netatmo.ylu.gofoot.model.RemoteKey
import com.netatmo.ylu.gofoot.retrofit.RequestClient
import com.netatmo.ylu.gofoot.room.GoFootRoomDatabase
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PlayerRemoteMediator(
    private val teamId: Int,
    private val season: Int,
    private val database: GoFootRoomDatabase
) : RemoteMediator<Int, Player>() {

    override suspend fun initialize(): InitializeAction {
        /*val cacheTimeout = TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS)
        return if (System.currentTimeMillis() - database.lastUpdated() >= cacheTimeout) {
            // Cached data is up-to-date, so there is no need to re-fetch
            // from the network.
            InitializeAction.SKIP_INITIAL_REFRESH
        } else {
            // Need to refresh cached data from network; returning
            // LAUNCH_INITIAL_REFRESH here will also block RemoteMediator's
            // APPEND and PREPEND from running until REFRESH succeeds.
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }*/
        //LAUNCH_INITIAL_REFRESH trigger REFRESH, PREPEND and APPEND
        //SKIP_INITIAL_REFRESH trigger PREPEND and APPEND
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Player>): MediatorResult {
        Log.v("PlayerRemoteMediator", loadType.name)
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {

                    val remoteKey = database.withTransaction {
                        database.remoteKeyDao().remoteKeyByQuery(teamId)
                    }

                    if (remoteKey.nextKey == null) {
                        return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    }
                    remoteKey.nextKey
                }
            }

            Log.v("PlayerRemoteMediator", "making request, loadkey $loadKey")
            val response = RequestClient.getPlayersByTeamId(teamId, season, loadKey ?: 1)

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    database.remoteKeyDao().deleteByQuery(teamId)
                }

                val nextKey = response.paging?.let {
                    if (it.total == it.current) {
                        null
                    } else {
                        it.current.plus(1).coerceAtMost(it.total)
                    }
                }

                database.remoteKeyDao().insertOrReplace(
                    RemoteKey(teamId, nextKey)
                )

                database.playerDao()
                    .insertPlayer(response.response.map { it.player.copy(teamId = teamId) })

                MediatorResult.Success(
                    endOfPaginationReached = response.paging?.total == response.paging?.current
                )
            }
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}