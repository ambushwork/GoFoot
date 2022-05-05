package com.netatmo.ylu.gofoot.model.standing

import com.google.gson.annotations.SerializedName

data class StandingLeague(
    @SerializedName("id") val id: Int,
    @SerializedName("standings") val standings: List<List<Standing>>
)