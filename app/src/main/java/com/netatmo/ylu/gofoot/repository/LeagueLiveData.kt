package com.netatmo.ylu.gofoot.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.netatmo.ylu.gofoot.model.League
import com.netatmo.ylu.gofoot.retrofit.RequestClient
import kotlinx.coroutines.launch

class LeagueLiveData : ViewModel() {
    val liveData: MutableLiveData<List<League>> by lazy {
        MutableLiveData<List<League>>().also {
            update()
        }
    }

    private fun update() {
        viewModelScope.launch {
            RequestClient.getAllLeague().response.map {
                it.league
            }.let {
                liveData.value = it
            }
        }
    }

}