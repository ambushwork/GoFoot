package com.netatmo.ylu.gofoot.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.netatmo.ylu.gofoot.model.League
import com.netatmo.ylu.gofoot.util.singleArgViewModelFactory
import kotlinx.coroutines.launch

class LeagueViewModel(private val repository: LeagueRepository) : ViewModel() {

    companion object {
        val FACTORY = singleArgViewModelFactory(::LeagueViewModel)
    }

    fun getLeagues(): LiveData<List<League>> = repository.allLeagues

    fun update() {
        viewModelScope.launch {
            repository.updateAll()
        }
    }
}