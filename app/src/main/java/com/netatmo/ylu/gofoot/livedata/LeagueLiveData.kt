package com.netatmo.ylu.gofoot.livedata

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.netatmo.ylu.gofoot.model.Body
import com.netatmo.ylu.gofoot.model.League
import com.netatmo.ylu.gofoot.model.LeagueResponse
import com.netatmo.ylu.gofoot.retrofit.RequestClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LeagueLiveData : ViewModel() {
    val liveData: MutableLiveData<List<League>> by lazy {
        MutableLiveData<List<League>>().also {
            update()
        }
    }

    private fun update() {
        RequestClient.getLeague(id = "39", callback = object : Callback<Body<LeagueResponse>> {
            override fun onResponse(
                call: Call<Body<LeagueResponse>>,
                response: Response<Body<LeagueResponse>>
            ) {
                response.body()?.response?.map {
                    it.league
                }?.let {
                    liveData.value = it
                }
            }

            override fun onFailure(call: Call<Body<LeagueResponse>>, t: Throwable) {
                Log.e("LeagueLiveData", "fail")
            }

        })
    }

}