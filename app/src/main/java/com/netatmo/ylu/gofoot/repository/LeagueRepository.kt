package com.netatmo.ylu.gofoot.repository

import androidx.annotation.WorkerThread
import com.netatmo.ylu.gofoot.retrofit.RequestClient
import com.netatmo.ylu.gofoot.room.league.LeagueDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LeagueRepository(private val dao: LeagueDao) {

    val allLeagues = dao.getAllLeaguesLiveData()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateAll() {
        withContext(Dispatchers.IO) {
            if (dao.getAllLeagues().isNullOrEmpty()) {
                val result = RequestClient.getAllLeague()
                dao.insertLeagues(result.response.map {
                    it.league
                })
            }
        }

    }
}