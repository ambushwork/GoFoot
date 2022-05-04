package com.netatmo.ylu.gofoot.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.netatmo.ylu.gofoot.model.League
import com.netatmo.ylu.gofoot.util.singleArgViewModelFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeagueViewModel @Inject constructor(private val repository: LeagueRepository) : ViewModel() {

    companion object {
        //to remove
        val FACTORY = singleArgViewModelFactory(::LeagueViewModel)
    }

    private val leagues: LiveData<List<League>> = repository.allLeagues

    val favLeagues: LiveData<List<League>> = leagues.map { leagues ->
        leagues.filter { it.favorite }
    }

    val unFavLeagues: LiveData<List<League>> = leagues.map { leagues ->
        leagues.filter { !it.favorite }
    }

    fun getLeaguesByIds(ids: List<String>): LiveData<List<League>> = repository.getLeaguesByIds(ids)

    fun setLocalFavLeague(id: String, fav: Boolean) =
        viewModelScope.launch {
            repository.setFavLeague(id, fav)
        }

    fun update() {
        viewModelScope.launch {
            repository.updateAll()
        }
    }
}