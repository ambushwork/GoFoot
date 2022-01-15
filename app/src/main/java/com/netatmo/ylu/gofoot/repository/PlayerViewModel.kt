package com.netatmo.ylu.gofoot.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.netatmo.ylu.gofoot.model.Player
import com.netatmo.ylu.gofoot.util.singleArgViewModelFactory
import kotlinx.coroutines.launch

class PlayerViewModel(private val playerRepository: PlayerRepository) : ViewModel() {

    companion object {
        val FACTORY = singleArgViewModelFactory(::PlayerViewModel)
    }

    fun getPlayerByTeamId(id: Int): LiveData<List<Player>> {
        return playerRepository.getLiveData(id)
    }

    fun update(id: Int) {
        viewModelScope.launch {
            playerRepository.getPlayersByTeamId(id)
        }
    }
}