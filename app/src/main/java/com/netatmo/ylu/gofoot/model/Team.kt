package com.netatmo.ylu.gofoot.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "team_table")
data class Team(
    @PrimaryKey
    @ColumnInfo(name = "team_id")
    @SerializedName("id") val id: Int,
    @ColumnInfo(name = "team_name")
    @SerializedName("name") val name: String,
    @ColumnInfo(name = "team_country")
    @SerializedName("country") val country: String,
    @ColumnInfo(name = "team_founded")
    @SerializedName("founded") val founded: Int,
    @ColumnInfo(name = "team_national")
    @SerializedName("national") val national: Boolean,
    @ColumnInfo(name = "team_logo")
    @SerializedName("logo") val logo: String
) {
}