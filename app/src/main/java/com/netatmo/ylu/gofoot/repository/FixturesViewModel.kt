package com.netatmo.ylu.gofoot.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.netatmo.ylu.gofoot.model.fixture.FixtureResponse
import com.netatmo.ylu.gofoot.retrofit.RequestClient
import com.netatmo.ylu.gofoot.util.getCurrentDate
import com.netatmo.ylu.gofoot.util.getCurrentSeason
import com.netatmo.ylu.gofoot.util.getFutureDate
import kotlinx.coroutines.launch

class FixturesViewModel : ViewModel() {
    val liveData: MutableLiveData<List<FixtureResponse>> by lazy {
        MutableLiveData<List<FixtureResponse>>()
    }

    fun liveUpdate() {
        viewModelScope.launch {
            liveData.value = RequestClient.getLiveFixtures().response
        }
    }

    fun getIncomingFixtures(teamId: Int) {
        viewModelScope.launch {
            liveData.value =
                RequestClient.getFixtures(
                    teamId,
                    getCurrentSeason(),
                    getCurrentDate(),
                    getFutureDate(3)
                ).response
        }
    }
}