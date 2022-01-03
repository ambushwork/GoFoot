package com.netatmo.ylu.gofoot.model

import com.google.gson.annotations.SerializedName

data class TeamResponse(
    @SerializedName("team") val team: Team,
    @SerializedName("venue") val venue: Venue
)