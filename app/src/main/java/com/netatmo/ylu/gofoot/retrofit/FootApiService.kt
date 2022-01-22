package com.netatmo.ylu.gofoot.retrofit

import com.netatmo.ylu.gofoot.model.*
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Query

interface FootApiService {

    @GET("countries")
    suspend fun getCountries(
        @HeaderMap headers: Map<String, String>,
        @Query("name") name: String
    ): Body<Country>

    @GET("leagues")
    suspend fun getLeagues(
        @HeaderMap headers: Map<String, String>,
        @Query("id") id: String? = null
    ): Body<LeagueResponse>

    @GET("teams")
    suspend fun getTeams(
        @HeaderMap headers: Map<String, String>,
        @Query("id") id: String
    ): Body<TeamInfo>

    @GET("players")
    suspend fun getPlayers(
        @HeaderMap headers: Map<String, String>,
        @Query("team") id: Int,
        @Query("season") season: Int,
        @Query("page") paging: Int,
    ): Body<PlayerResponse>

    @GET("teams")
    suspend fun getTeams(
        @HeaderMap headers: Map<String, String>,
        @Query("league") league: String,
        @Query("season") season: Int
    ): Body<TeamInfo>

    @GET("fixtures")
    suspend fun getFixturesByLive(
        @HeaderMap headers: Map<String, String>,
        @Query("live") live: String
    ): Body<FixtureResponse>

}