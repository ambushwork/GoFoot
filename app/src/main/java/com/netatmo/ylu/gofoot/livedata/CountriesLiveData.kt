package com.netatmo.ylu.gofoot.livedata

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.netatmo.ylu.gofoot.model.Body
import com.netatmo.ylu.gofoot.model.Country
import com.netatmo.ylu.gofoot.retrofit.RequestClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountriesLiveData : ViewModel() {

    val countryLiveData: MutableLiveData<List<Country>> by lazy {
        MutableLiveData<List<Country>>().also {
            load()
        }
    }

    fun setValue(countries: List<Country>) {
        countryLiveData.value = countries
    }

    fun getValue(): MutableLiveData<List<Country>> {
        return countryLiveData
    }

    private fun load() {
        RequestClient.getCountry("england", object : Callback<Body<Country>> {
            override fun onResponse(call: Call<Body<Country>>, response: Response<Body<Country>>) {
                Log.d("TAG", "success")
                response.body()?.response?.let {
                    setValue(it)
                }
            }

            override fun onFailure(call: Call<Body<Country>>, t: Throwable) {
                Log.d("TAG", t.message ?: "")
            }

        })
    }
}