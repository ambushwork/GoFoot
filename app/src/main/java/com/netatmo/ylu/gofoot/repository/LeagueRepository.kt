package com.netatmo.ylu.gofoot.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.netatmo.ylu.gofoot.model.League
import com.netatmo.ylu.gofoot.retrofit.RequestClient
import com.netatmo.ylu.gofoot.room.league.LeagueDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LeagueRepository(private val dao: LeagueDao) {

    val allLeagues = dao.getAllLeaguesLiveData()

    fun getLeaguesByIds(ids: List<String>): LiveData<List<League>> {
        return dao.getLeaguesByIds(ids)
    }

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