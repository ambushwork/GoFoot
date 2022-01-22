package com.netatmo.ylu.gofoot.retrofit

import com.netatmo.ylu.gofoot.constants.*
import com.netatmo.ylu.gofoot.model.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RequestClient {
    private val service: FootApiService
    private val headers by lazy {
        HashMap<String, String>().apply {
            this[X_RAPIDAPI_HOST_KEY] = X_RAPIDAPI_HOST_VALUE
            this[X_RAPIDAPI_KEY_KEY] = X_RAPIDAPI_KEY_VALUE
        }
    }

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(FootApiService::class.java)
    }

    suspend fun getCountry(name: String): Body<Country> = service.getCountries(headers, name)

    suspend fun getAllLeague(): Body<LeagueResponse> = service.getLeagues(headers)

    suspend fun getLeague(id: String? = null): Body<LeagueResponse> =
        service.getLeagues(headers, id)

    suspend fun getTeam(id: String): Body<TeamInfo> = service.getTeams(headers, id)

    suspend fun getPlayersByTeamId(id: Int, season: Int, paging: Int): Body<PlayerResponse> =
        service.getPlayers(headers, id, season, paging)

    suspend fun getTeamByLeagueSeason(league: String, season: Int): Body<TeamInfo> =
        service.getTeams(headers, league = league, season = season)

    suspend fun getLiveFixtures(): Body<FixtureResponse> = service.getFixturesByLive(headers, "all")

}