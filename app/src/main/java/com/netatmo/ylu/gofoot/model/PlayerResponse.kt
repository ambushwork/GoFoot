package com.netatmo.ylu.gofoot.model

import com.google.gson.annotations.SerializedName

data class PlayerResponse(@SerializedName("player") val player: Player)