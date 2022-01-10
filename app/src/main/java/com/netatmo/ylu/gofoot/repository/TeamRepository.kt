package com.netatmo.ylu.gofoot.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.netatmo.ylu.gofoot.model.TeamInfo
import com.netatmo.ylu.gofoot.model.TeamVenueCrossRef
import com.netatmo.ylu.gofoot.retrofit.RequestClient
import com.netatmo.ylu.gofoot.room.TeamDao

class TeamRepository(private val teamDao: TeamDao) {

    val allTeams: LiveData<List<TeamInfo>> = teamDao.getTeamsLivedata()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateTeam(leagueId: String, season: Int) {
        val result = RequestClient.getTeamByLeagueSeason(leagueId, season)
        teamDao.insertTeamAndVenue(
            result.response.map { it.team },
            result.response.map { it.venue })
        teamDao.insertJoint(result.response.map {
            TeamVenueCrossRef(it.team.teamId, it.venue.venueId)
        })
    }

}