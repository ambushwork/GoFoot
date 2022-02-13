package com.netatmo.ylu.gofoot.model.player

import com.google.gson.annotations.SerializedName

data class Goals(
    @SerializedName("total") val total: Int,
    @SerializedName("assists") val assists: Int,
    @SerializedName("saves") val saves: Int
)