package com.netatmo.ylu.gofoot.model

import com.google.gson.annotations.SerializedName

data class LeagueResponse(
    @SerializedName("league") val league: League,
    @SerializedName("country") val country: Country,
    @SerializedName("seasons") val seasons: List<Season>
)