package com.netatmo.ylu.gofoot.model

import com.google.gson.annotations.SerializedName

data class Coverage(
    @SerializedName("appearences") val appearences: Int,
    @SerializedName("lineups") val lineups: Int,
    @SerializedName("minutes") val minutes: Int,
    @SerializedName("position") val position: String,
    @SerializedName("rating") val rating: Float,
    @SerializedName("captain") val captain: Boolean
) {
}