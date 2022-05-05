package com.netatmo.ylu.gofoot.model.standing

import com.google.gson.annotations.SerializedName

data class StandingsResponse(@SerializedName("league") val league: StandingLeague)