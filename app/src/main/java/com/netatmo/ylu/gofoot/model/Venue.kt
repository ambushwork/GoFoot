package com.netatmo.ylu.gofoot.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "table_venue")
data class Venue(
    @PrimaryKey
    @SerializedName("id") val venueId: String,

    @SerializedName("name") val name: String,

    @SerializedName("address") val address: String,

    @SerializedName("city") val city: String,

    @SerializedName("capacity") val capacity: Int,

    @SerializedName("surface") val surface: String,

    @SerializedName("image") val image: String,

) {
}