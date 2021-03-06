package com.netatmo.ylu.gofoot.model

import com.google.gson.annotations.SerializedName

data class Season(
    @SerializedName("year") val year: Int,
    @SerializedName("start") val start: String,
    @SerializedName("end") val end: String,
    @SerializedName("current") val current: Boolean,
    @SerializedName("coverage") val coverage: Coverage
)