package com.netatmo.ylu.gofoot.repository

import androidx.annotation.WorkerThread
import com.netatmo.ylu.gofoot.retrofit.RequestClient
import com.netatmo.ylu.gofoot.room.player.PlayerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PlayerRepository(private val playerDao: PlayerDao) {

    companion object {
        private const val SEASON = 2021
    }

    fun getLiveData(teamId: Int) = playerDao.getPlayerLiveDataByTeamId(teamId)

    @WorkerThread
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
    }
}