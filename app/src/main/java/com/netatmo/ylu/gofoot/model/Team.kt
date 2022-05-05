package com.netatmo.ylu.gofoot.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "team_table")
data class Team(
    @PrimaryKey
    @SerializedName("id") val teamId: Int,
    @SerializedName("name") val name: String,
    @SerializedName("country") val country: String?,
    @SerializedName("founded") val founded: Int?,
    @SerializedName("national") val national: Boolean?,
    @SerializedName("logo") val logo: String,
    val leagueId: String
) {
}