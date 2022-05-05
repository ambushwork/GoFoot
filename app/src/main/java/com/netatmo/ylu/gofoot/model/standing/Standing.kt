package com.netatmo.ylu.gofoot.model.standing

import com.google.gson.annotations.SerializedName
import com.netatmo.ylu.gofoot.model.StandingHistory
import com.netatmo.ylu.gofoot.model.Team

data class Standing(
    @SerializedName("rank") val rank: Int,
    @SerializedName("team") val team: Team,
    @SerializedName("points") val points: Int,
    @SerializedName("goalsDiff") val goalsDiff: Int,
    @SerializedName("all") val all: StandingHistory,
    @SerializedName("home") val home: StandingHistory,
    @SerializedName("away") val away: StandingHistory,
)