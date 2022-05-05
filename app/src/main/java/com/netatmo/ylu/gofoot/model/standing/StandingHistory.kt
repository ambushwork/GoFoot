package com.netatmo.ylu.gofoot.model

import com.google.gson.annotations.SerializedName

data class StandingHistory(
    @SerializedName("played") val played: Int,
    @SerializedName("win") val win: Int,
    @SerializedName("draw") val draw: Int,
    @SerializedName("lose") val lose: Int,
    @SerializedName("goals") val goals: Goals,
)

data class Goals(
    @SerializedName("for") val _for: Int,
    @SerializedName("against") val against: Int,
)