package com.netatmo.ylu.gofoot.retrofit

import android.content.Context
import android.util.Log
import com.netatmo.ylu.gofoot.constants.*
import com.netatmo.ylu.gofoot.model.*
import com.netatmo.ylu.gofoot.model.fixture.FixtureResponse
import com.netatmo.ylu.gofoot.model.standing.StandingsResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RequestClient(@ApplicationContext val context: Context) {
    private val service: FootApiService
    private val headers by lazy {
        HashMap<String, String>().apply {
            this[X_RAPIDAPI_HOST_KEY] = X_RAPIDAPI_HOST_VALUE
            this[X_RAPIDAPI_KEY_KEY] = X_RAPIDAPI_KEY_VALUE
        }
    }

    companion object {
        const val CACHE_CONTROL_HEADER = "Cache-Control"
        const val CACHE_CONTROL_NO_CACHE = "no-cache"
    }

    init {

        val cacheSize = (5.times(1024).times(1024)).toLong()
        val myCache = Cache(context.cacheDir, cacheSize)
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)
        val okHttpClient = OkHttpClient.Builder()
            .cache(myCache)
            .addNetworkInterceptor(CacheInterceptor())
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(FootApiService::class.java)
    }

    class CacheInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val originalResponse = chain.proceed(request)
            Log.i("CacheInterceptor", "Request proceeded, not from cache!")
            val shouldUseCache = request.header(CACHE_CONTROL_HEADER) != CACHE_CONTROL_NO_CACHE
            if (!shouldUseCache) return originalResponse

            val cacheControl = CacheControl.Builder()
                .maxAge(10, TimeUnit.MINUTES)
                .build()

            return originalResponse.newBuilder()
                .header(CACHE_CONTROL_HEADER, cacheControl.toString())
                .build()
        }
    }

    suspend fun getCountry(name: String): Body<Country> = service.getCountries(headers, name)

    suspend fun getAllLeague(): Body<LeagueResponse> = service.getLeagues(headers)

    suspend fun getLeague(id: String? = null): Body<LeagueResponse> =
        service.getLeagues(headers, id)

    suspend fun getTeam(id: String): Body<TeamInfo> = service.getTeams(headers, id)

    suspend fun getPlayersByTeamId(id: Int, season: Int, paging: Int): Body<PlayerResponse> =
        service.getPlayers(headers, id, season, paging)

    suspend fun getPlayerId(id: Int, season: String): Body<PlayerResponse> =
        service.getPlayerById(headers, id, season)

    suspend fun getTeamByLeagueSeason(league: String, season: Int): Body<TeamInfo> =
        service.getTeams(headers, league = league, season = season)

    suspend fun getLiveFixtures(): Body<FixtureResponse> = service.getFixturesByLive(headers, "all")

    suspend fun getFixtures(
        teamId: Int,
        season: String,
        from: String,
        to: String
    ): Body<FixtureResponse> = service.getFixtures(headers, season, teamId, from, to)

    suspend fun getStandings(
        season: Int,
        league: Int
    ): Body<StandingsResponse> = service.getStandings(
        headers = headers,
        season = season,
        league = league
    )
}