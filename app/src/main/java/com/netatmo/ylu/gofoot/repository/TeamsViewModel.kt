package com.netatmo.ylu.gofoot.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.netatmo.ylu.gofoot.model.TeamInfo
import com.netatmo.ylu.gofoot.util.singleArgViewModelFactory
import kotlinx.coroutines.launch

class TeamsViewModel(private val repository: TeamRepository) : ViewModel() {

    companion object {

        val FACTORY = singleArgViewModelFactory(::TeamsViewModel)
    }

    fun getTeamsByLeagueId(leagueId: String): LiveData<List<TeamInfo>> {
        return repository.getTeamsByLeagueId(leagueId)
    }

    fun getTeamById(id: Int): LiveData<TeamInfo> {
        return repository.getTeamById(id)
    }

    fun update(leagueId: String, season: Int) {
        viewModelScope.launch {
            repository.updateTeam(leagueId, season)
        }
    }
}

fun List<TeamInfo>.getTeam(id: Int): TeamInfo? {
    return this.firstOrNull {
        it.team.teamId == id
    }
}