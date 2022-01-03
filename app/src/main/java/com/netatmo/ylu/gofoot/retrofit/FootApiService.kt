package com.netatmo.ylu.gofoot.retrofit

import com.netatmo.ylu.gofoot.model.*
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

    @GET("teams")
    fun getTeams(
        @HeaderMap headers: Map<String, String>,
        @Query("id") id: String
    ): Call<Body<TeamResponse>>

    @GET("teams")
    fun getTeams(
        @HeaderMap headers: Map<String, String>,
        @Query("league") league: String,
        @Query("season") season: Int
    ): Call<Body<TeamResponse>>

    @GET("fixtures")
    fun getFixturesByLive(
        @HeaderMap headers: Map<String, String>,
        @Query("live") live: String
    ): Call<Body<FixtureResponse>>

}