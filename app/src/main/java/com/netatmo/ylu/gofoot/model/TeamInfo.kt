package com.netatmo.ylu.gofoot.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.google.gson.annotations.SerializedName

data class TeamInfo(
    @Embedded
    @SerializedName("team") val team: Team,
    @Relation(
        parentColumn = "teamId",
        entityColumn = "venueId",
        associateBy = Junction(TeamVenueCrossRef::class)
    )
    @SerializedName("venue") val venue: Venue
)