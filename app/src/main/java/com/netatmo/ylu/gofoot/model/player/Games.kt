package com.netatmo.ylu.gofoot.model.player

import com.google.gson.annotations.SerializedName

data class Games(
    @SerializedName("appearences") val appearances: Int,
    @SerializedName("lineups") val lineups: Int,
    @SerializedName("minutes") val minutes: Int,
    @SerializedName("position") val position: String,
    @SerializedName("rating") val rating: String
) {
}