package com.netatmo.ylu.gofoot.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "table_leagues")
data class League(
    @PrimaryKey
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("type") val type: LeagueType,
    @SerializedName("logo") val logo: String,
    val favorite: Boolean = false
)
