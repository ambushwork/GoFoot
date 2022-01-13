package com.netatmo.ylu.gofoot.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.netatmo.ylu.gofoot.model.TeamInfo
import com.netatmo.ylu.gofoot.util.singleArgViewModelFactory
import kotlinx.coroutines.launch

class TeamsViewModel(private val repository: TeamRepository) : ViewModel() {

    companion object {
        val FACTORY = singleArgViewModelFactory(::TeamsViewModel)
    }

    private val _spinner = MutableLiveData(false)

    val spinner: LiveData<Boolean>
        get() = _spinner


    fun getTeamsByLeagueId(leagueId: String): LiveData<List<TeamInfo>> {
        return repository.getTeamsByLeagueId(leagueId)
    }

    fun update(leagueId: String, season: Int) {
        viewModelScope.launch {
            _spinner.value = true
            repository.updateTeam(leagueId, season)
            _spinner.value = false
        }
    }
}
