package com.netatmo.ylu.gofoot.retrofit

import com.netatmo.ylu.gofoot.model.Body
import com.netatmo.ylu.gofoot.model.Country
import com.netatmo.ylu.gofoot.model.LeagueResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Query

interface FootApiService {

    @GET("countries")
    fun getCountries(
        @HeaderMap headers: Map<String, String>,
        @Query("name") name: String
    ): Call<Body<Country>>

    @GET("leagues")
    fun getLeagues(
        @HeaderMap headers: Map<String, String>,
        @Query("id") id: String?
    ): Call<Body<LeagueResponse>>

}