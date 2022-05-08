package com.netatmo.ylu.gofoot.ui.live

import androidx.lifecycle.*
import com.netatmo.ylu.gofoot.repository.FixturesRepository
import com.netatmo.ylu.gofoot.repository.LeagueRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LiveViewModel @Inject constructor(
    leagueRepository: LeagueRepository,
    private val fixturesRepository: FixturesRepository
) : ViewModel() {

    private var firstLoad = false

    val loading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    val shimmerState = loading.map {
        !firstLoad && it
    }

    private val isFiltered = MutableStateFlow(false)

    private val favLeaguesFlow = leagueRepository.allLeagues.map { leagues ->
        leagues.filter {
            it.favorite
        }
    }.asFlow()

    private val fixturesFlow = fixturesRepository.liveFixtures.onStart {
        updateLiveFixtures()
    }

    val resultFlow =
        combine(
            isFiltered,
            favLeaguesFlow,
            fixturesFlow
        ) { isFiltered, favs, fixtures ->
            val filteredLeagues = if (isFiltered) {
                fixtures.filter {
                    favs.map { favLeague ->
                        favLeague.id
                    }.contains(it.league.id)
                }
            } else {
                fixtures
            }
            filteredLeagues
        }.asLiveData()

    fun updateLiveFixtures() {
        viewModelScope.launch {
            loading.value = true
            fixturesRepository.updateLiveFixtures()
            loading.value = false
        }
    }

    fun toggleFiltered() {
        isFiltered.value = !isFiltered.value
    }
}