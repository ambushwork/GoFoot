package com.netatmo.ylu.gofoot.model

import com.google.gson.annotations.SerializedName

enum class LeagueType(val value: String) {
    @SerializedName("Cup")
    CUP("cup"),

    @SerializedName("League")
    LEAGUE("league")
}