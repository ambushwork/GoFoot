package com.netatmo.ylu.gofoot.livedata

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.netatmo.ylu.gofoot.model.Body
import com.netatmo.ylu.gofoot.model.Team
import com.netatmo.ylu.gofoot.model.TeamResponse
import com.netatmo.ylu.gofoot.retrofit.RequestClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamsLiveData : ViewModel() {
    val liveData: MutableLiveData<List<Team>> by lazy {
        MutableLiveData<List<Team>>()
    }

    fun update(leagueId: String, season: Int) {
        RequestClient.getTeamByLeagueSeason(
            league = leagueId,
            season = season,
            callback = object : Callback<Body<TeamResponse>> {
                override fun onResponse(
                    call: Call<Body<TeamResponse>>,
                    response: Response<Body<TeamResponse>>
                ) {
                    response.body()?.response?.map {
                        it.team
                    }?.let {
                        liveData.value = it
                    }
                }

                override fun onFailure(call: Call<Body<TeamResponse>>, t: Throwable) {
                    Log.e("TeamLiveData", "fail")
                }

            })
    }
}