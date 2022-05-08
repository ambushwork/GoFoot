package com.netatmo.ylu.gofoot.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.netatmo.ylu.gofoot.model.TeamInfo
import com.netatmo.ylu.gofoot.model.TeamVenueCrossRef
import com.netatmo.ylu.gofoot.model.standing.Standing
import com.netatmo.ylu.gofoot.retrofit.RequestClient
import com.netatmo.ylu.gofoot.room.TeamDao

class TeamRepository(
    private val teamDao: TeamDao,
    private val requestClient: RequestClient
) {

    fun getTeamsByLeagueId(id: String): LiveData<List<TeamInfo>> {
        return teamDao.getTeamsByLeagueId(id)
    }

    fun getTeamById(id: Int): LiveData<TeamInfo> {
        return teamDao.getTeamById(id)
    }

    @Suppress
    @WorkerThread
    suspend fun updateStandings(leagueId: Int, season: Int): List<Standing> {
        return requestClient.getStandings(
            season = season,
            league = leagueId
        ).response.firstOrNull()?.league?.standings?.firstOrNull() ?: emptyList()
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateTeam(leagueId: String, season: Int) {
        val result = requestClient.getTeamByLeagueSeason(leagueId, season)
        teamDao.insertTeamAndVenue(
            result.response.map { it.team.copy(leagueId = leagueId) },
            result.response.map { it.venue })
        teamDao.insertJoint(result.response.map {
            TeamVenueCrossRef(it.team.teamId, it.venue.venueId)
        })
    }


}