package com.netatmo.ylu.gofoot.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.netatmo.ylu.gofoot.model.Player
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor(private val playerRepository: PlayerRepository) :
    ViewModel() {

    private val _playerList = MutableLiveData<PagingData<Player>>()

    fun getPlayerByTeamId(id: Int): LiveData<PagingData<Player>> {
        val response = playerRepository.getPlayersResultStream(id)
        _playerList.value = response.value
        return response
    }

}