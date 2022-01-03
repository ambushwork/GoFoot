package com.netatmo.ylu.gofoot.model

import com.google.gson.annotations.SerializedName

/**
 * https://www.api-football.com/documentation-v3#operation/get-fixtures
 */
data class Fixture(
    @SerializedName("id") val id: Int,
    @SerializedName("referee") val referee: String?,
    @SerializedName("timezone") val timeZone: String,
    @SerializedName("date") val date: String,
    @SerializedName("periods") val periods: Periods,
    @SerializedName("venue") val venue: Venue,
    @SerializedName("status") val status: Status,
)