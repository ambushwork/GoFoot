package com.netatmo.ylu.gofoot.retrofit

import com.netatmo.ylu.gofoot.constants.*
import com.netatmo.ylu.gofoot.model.*
import retrofit2.Callback
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
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(FootApiService::class.java)
    }

    fun getCountry(name: String, callback: Callback<Body<Country>>) {
        val call = service.getCountries(headers, name)
        call.enqueue(callback)
    }

    fun getLeague(id: String? = null, callback: Callback<Body<LeagueResponse>>) {
        val call = service.getLeagues(headers, id)
        call.enqueue(callback)
    }

    fun getTeam(
        id: String,
        callback: Callback<Body<TeamResponse>>
    ) {
        val call = service.getTeams(headers, id)
        call.enqueue(callback)
    }

    fun getTeamByLeagueSeason(
        league: String,
        season: Int,
        callback: Callback<Body<TeamResponse>>
    ) {
        val call = service.getTeams(
            headers,
            league = league,
            season = season
        )
        call.enqueue(callback)
    }

    fun getLiveFixtures(callback: Callback<Body<FixtureResponse>>) {
        val call = service.getFixturesByLive(headers, "all")
        call.enqueue(callback)
    }

}