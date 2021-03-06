package com.netatmo.ylu.gofoot.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.netatmo.ylu.gofoot.model.TeamInfo
import com.netatmo.ylu.gofoot.model.standing.Standing
import com.netatmo.ylu.gofoot.util.getCurrentSeason
import com.netatmo.ylu.gofoot.util.singleArgViewModelFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamsViewModel @Inject constructor(private val repository: TeamRepository) : ViewModel() {

    companion object {
        val FACTORY = singleArgViewModelFactory(::TeamsViewModel)
    }

    private val _spinner = MutableLiveData(false)

    val spinner: LiveData<Boolean>
        get() = _spinner

    private val _standings = MutableLiveData<List<Standing>>()

    val standings: LiveData<List<Standing>>
        get() = _standings

    fun getTeamsByLeagueId(leagueId: String): LiveData<List<TeamInfo>> {
        return repository.getTeamsByLeagueId(leagueId)
    }

    fun updateStandings(leagueId: String) {
        viewModelScope.launch {
            _spinner.value = true
            _standings.value = repository.updateStandings(
                leagueId = leagueId.toInt(),
                season = getCurrentSeason().toInt()
            )
            _spinner.value = false
        }
    }

    fun update(leagueId: String, season: Int) {
        viewModelScope.launch {
            _spinner.value = true
            repository.updateTeam(leagueId, season)
            _spinner.value = false
        }
    }
}
