package com.netatmo.ylu.gofoot.model.fixture

import com.google.gson.annotations.SerializedName

data class FixtureGoals(
    @SerializedName("home") val home: Int,
    @SerializedName("away") val away: Int
)