package com.netatmo.ylu.gofoot.model.fixture

import com.google.gson.annotations.SerializedName
import com.netatmo.ylu.gofoot.model.Team

data class FixtureTeams(
    @SerializedName("home") val home: Team,
    @SerializedName("away") val away: Team
)