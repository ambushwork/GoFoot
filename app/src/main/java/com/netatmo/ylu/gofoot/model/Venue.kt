package com.netatmo.ylu.gofoot.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "table_venue")
data class Venue(
    @PrimaryKey
    @ColumnInfo(name = "venue_id")
    @SerializedName("id") val venueId: String,

    @ColumnInfo(name = "venue_name")
    @SerializedName("name") val name: String,

    @ColumnInfo(name = "venue_address")
    @SerializedName("address") val address: String,

    @ColumnInfo(name = "venue_city")
    @SerializedName("city") val city: String,

    @ColumnInfo(name = "venue_capacity")
    @SerializedName("capacity") val capacity: Int,

    @ColumnInfo(name = "venue_surface")
    @SerializedName("surface") val surface: String,

    @ColumnInfo(name = "venue_image")
    @SerializedName("image") val image: String,

    //table relation
    val teamId: Int
) {
}