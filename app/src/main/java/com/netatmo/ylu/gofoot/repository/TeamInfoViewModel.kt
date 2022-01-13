package com.netatmo.ylu.gofoot.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.netatmo.ylu.gofoot.model.TeamInfo
import com.netatmo.ylu.gofoot.util.singleArgViewModelFactory

class TeamInfoViewModel(private val teamRepository: TeamRepository) : ViewModel() {

    companion object {
        val FACTORY = singleArgViewModelFactory(::TeamInfoViewModel)
    }

    fun getTeamById(id: Int): LiveData<TeamInfo> {
        return teamRepository.getTeamById(id)
    }

}