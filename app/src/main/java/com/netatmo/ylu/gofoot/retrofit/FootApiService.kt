package com.netatmo.ylu.gofoot.retrofit

import com.netatmo.ylu.gofoot.model.*
import com.netatmo.ylu.gofoot.model.fixture.FixtureResponse
import com.netatmo.ylu.gofoot.model.standing.StandingsResponse
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

    @GET("players")
    suspend fun getPlayerById(
        @HeaderMap headers: Map<String, String>,
        @Query("id") id: Int,
        @Query("season") season: String
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

    @GET("fixtures")
    suspend fun getFixtures(
        @HeaderMap headers: Map<String, String>,
        @Query("season") season: String,
        @Query("team") teamId: Int,
        @Query("from") fromDate: String,
        @Query("to") toDate: String
    ): Body<FixtureResponse>

    @GET("standings")
    suspend fun getStandings(
        @HeaderMap headers: Map<String, String>,
        @Query("season") season: Int,
        @Query("league") league: Int,
    ): Body<StandingsResponse>
}