package com.netatmo.ylu.gofoot.repository

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.netatmo.ylu.gofoot.model.Player
import com.netatmo.ylu.gofoot.paging.PlayerPagingSource
import com.netatmo.ylu.gofoot.paging.PlayerRemoteMediator
import com.netatmo.ylu.gofoot.room.GoFootRoomDatabase

class PlayerRepository(private val database: GoFootRoomDatabase) {

    companion object {
        private const val SEASON = 2021
    }

    //fun getLiveData(teamId: Int) = playerDao.getPlayerLiveDataByTeamId(teamId)

    /*   @WorkerThread
       suspend fun getPlayersByTeamId(teamId: Int) {
           withContext(Dispatchers.IO) {
               val list = playerDao.getPlayerByTeamId(teamId)
               if (list.isNullOrEmpty()) {
                   val result = RequestClient.getPlayersByTeamId(teamId, SEASON)
                   playerDao.insertPlayer(result.response.map {
                       it.player.copy(teamId = teamId)
                   })
               }
           }
       }*/

    @OptIn(ExperimentalPagingApi::class)
    fun getPlayersResultStream(teamId: Int): LiveData<PagingData<Player>> {
        return Pager(
            config = PagingConfig(
                pageSize = PlayerPagingSource.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            remoteMediator = PlayerRemoteMediator(teamId, SEASON, database)
            /* pagingSourceFactory = {

             }*/
        ) {
            //https://github.com/android/architecture-components-samples/blob/main/PagingWithNetworkSample/app/src/main/java/com/android/example/paging/pagingwithnetwork/reddit/repository/inDb/DbRedditPostRepository.kt
            //PlayerPagingSource(teamId, SEASON, database.playerDao())
            database.playerDao().getPlayerPagingDataByTeamId(teamId)
        }.liveData
    }
}