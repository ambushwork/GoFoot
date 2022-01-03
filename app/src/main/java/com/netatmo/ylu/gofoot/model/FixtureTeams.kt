package com.netatmo.ylu.gofoot.model

import com.google.gson.annotations.SerializedName

data class FixtureTeams(
    @SerializedName("home") val home: Team,
    @SerializedName("away") val away: Team
)