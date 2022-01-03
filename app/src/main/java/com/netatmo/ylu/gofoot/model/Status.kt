package com.netatmo.ylu.gofoot.model

import com.google.gson.annotations.SerializedName

data class Status(
    @SerializedName("long") val long: String,
    @SerializedName("short") val short: String,
    @SerializedName("elapsed") val elapsed: Int
) {
}