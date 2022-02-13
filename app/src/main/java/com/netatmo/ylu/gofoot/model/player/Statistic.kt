package com.netatmo.ylu.gofoot.model.player

import com.google.gson.annotations.SerializedName

data class Statistic(
    @SerializedName("games") val games: Games,
    @SerializedName("goals") val goals: Goals
) {
}