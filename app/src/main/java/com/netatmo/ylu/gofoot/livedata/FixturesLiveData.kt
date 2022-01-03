package com.netatmo.ylu.gofoot.livedata

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.netatmo.ylu.gofoot.model.Body
import com.netatmo.ylu.gofoot.model.FixtureResponse
import com.netatmo.ylu.gofoot.retrofit.RequestClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FixturesLiveData : ViewModel() {
    val liveData: MutableLiveData<List<FixtureResponse>> by lazy {
        MutableLiveData<List<FixtureResponse>>()
    }

    fun liveUpdate() {
        RequestClient.getLiveFixtures(object : Callback<Body<FixtureResponse>> {
            override fun onResponse(
                call: Call<Body<FixtureResponse>>,
                response: Response<Body<FixtureResponse>>
            ) {
                liveData.value = response.body()?.response
            }

            override fun onFailure(call: Call<Body<FixtureResponse>>, t: Throwable) {
                Log.e("FixturesLiveData", "fail")
            }

        })
    }
}