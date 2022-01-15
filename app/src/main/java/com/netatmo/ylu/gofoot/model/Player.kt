package com.netatmo.ylu.gofoot.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "table_player")
data class Player(
    @PrimaryKey
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("firstname") val firstName: String,
    @SerializedName("lastname") val lastName: String,
    @SerializedName("age") val age: Int,
    @SerializedName("nationality") val nationality: String,
    @SerializedName("height") val height: String?,
    @SerializedName("weight") val weight: String?,
    @SerializedName("injured") val injured: Boolean,
    @SerializedName("photo") val photo: String,
    val teamId: Int
)