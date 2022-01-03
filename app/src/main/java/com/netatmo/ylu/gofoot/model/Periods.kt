package com.netatmo.ylu.gofoot.model

import com.google.gson.annotations.SerializedName

data class Periods(
    @SerializedName("first") val first: Long,
    @SerializedName("second") val second: Long?
) {
}