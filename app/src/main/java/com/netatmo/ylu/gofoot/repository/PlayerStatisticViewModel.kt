package com.netatmo.ylu.gofoot.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.netatmo.ylu.gofoot.model.PlayerResponse
import com.netatmo.ylu.gofoot.retrofit.RequestClient
import com.netatmo.ylu.gofoot.util.getCurrentSeason
import kotlinx.coroutines.launch

class PlayerStatisticViewModel : ViewModel() {
    val liveData: MutableLiveData<PlayerResponse> by lazy {
        MutableLiveData()
    }

    fun getPlayer(playerId: Int) {
        viewModelScope.launch {
            liveData.value =
                RequestClient.getPlayerId(playerId, getCurrentSeason()).response.firstOrNull()
        }
    }

}