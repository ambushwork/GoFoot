package com.netatmo.ylu.gofoot.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.netatmo.ylu.gofoot.model.fixture.FixtureResponse
import com.netatmo.ylu.gofoot.retrofit.RequestClient
import com.netatmo.ylu.gofoot.util.getCurrentDate
import com.netatmo.ylu.gofoot.util.getCurrentSeason
import com.netatmo.ylu.gofoot.util.getFutureDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FixturesViewModel @Inject constructor(private val requestClient: RequestClient) :
    ViewModel() {
    val liveData: MutableLiveData<List<FixtureResponse>> by lazy {
        MutableLiveData<List<FixtureResponse>>()
    }

    val loading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    fun liveUpdate() {
        loading.value = true
        viewModelScope.launch {
            liveData.value = requestClient.getLiveFixtures().response
            loading.value = false
        }
    }

    fun getIncomingFixtures(teamId: Int) {
        viewModelScope.launch {
            liveData.value =
                requestClient.getFixtures(
                    teamId,
                    getCurrentSeason(),
                    getCurrentDate(),
                    getFutureDate(3)
                ).response
        }
    }
}