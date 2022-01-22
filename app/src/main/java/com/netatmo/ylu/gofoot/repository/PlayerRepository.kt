package com.netatmo.ylu.gofoot.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.netatmo.ylu.gofoot.model.Player
import com.netatmo.ylu.gofoot.paging.PlayerPagingSource
import com.netatmo.ylu.gofoot.room.player.PlayerDao

class PlayerRepository(private val playerDao: PlayerDao) {

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

    fun getPlayersResultStream(teamId: Int): LiveData<PagingData<Player>> {
        return Pager(
            config = PagingConfig(
                pageSize = PlayerPagingSource.NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                PlayerPagingSource(teamId, SEASON)
            }
        ).liveData
    }
}