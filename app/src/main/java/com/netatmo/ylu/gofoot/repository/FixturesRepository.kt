package com.netatmo.ylu.gofoot.repository

import com.netatmo.ylu.gofoot.model.fixture.FixtureResponse
import com.netatmo.ylu.gofoot.retrofit.RequestClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FixturesRepository(private val requestClient: RequestClient) {

    val liveFixtures: StateFlow<List<FixtureResponse>>
        get() = _liveFixtures

    private val _liveFixtures = MutableStateFlow(emptyList<FixtureResponse>())

    suspend fun updateLiveFixtures() {
        _liveFixtures.value = requestClient.getLiveFixtures().response
    }
}