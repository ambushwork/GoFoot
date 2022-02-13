package com.netatmo.ylu.gofoot.model

import com.google.gson.annotations.SerializedName
import com.netatmo.ylu.gofoot.model.player.Statistic

data class PlayerResponse(
    @SerializedName("player") val player: Player,
    @SerializedName("statistics") val statistics: List<Statistic>
)
