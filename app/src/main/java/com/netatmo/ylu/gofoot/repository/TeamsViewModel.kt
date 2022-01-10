package com.netatmo.ylu.gofoot.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.netatmo.ylu.gofoot.model.TeamInfo
import com.netatmo.ylu.gofoot.util.singleArgViewModelFactory
import kotlinx.coroutines.launch

class TeamsViewModel(private val repository: TeamRepository) : ViewModel() {

    companion object {

        val FACTORY = singleArgViewModelFactory(::TeamsViewModel)
    }

    val teams = repository.allTeams

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