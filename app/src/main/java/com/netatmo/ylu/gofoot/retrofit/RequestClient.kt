package com.netatmo.ylu.gofoot.retrofit

import com.netatmo.ylu.gofoot.constants.*
import com.netatmo.ylu.gofoot.model.Body
import com.netatmo.ylu.gofoot.model.Country
import com.netatmo.ylu.gofoot.model.LeagueResponse
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RequestClient {
    private val service: FootApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(FootApiService::class.java)
    }

    fun getCountry(name: String, callback: Callback<Body<Country>>) {
        val call = service.getCountries(getHeaderMap(), name)
        call.enqueue(callback)
    }

    fun getLeague(id: String? = null, callback: Callback<Body<LeagueResponse>>) {
        val call = service.getLeagues(getHeaderMap(), id)
        call.enqueue(callback)
    }


    private fun getHeaderMap(): Map<String, String> {
        return HashMap<String, String>().apply {
            this[X_RAPIDAPI_HOST_KEY] = X_RAPIDAPI_HOST_VALUE
            this[X_RAPIDAPI_KEY_KEY] = X_RAPIDAPI_KEY_VALUE
        }
    }
}