package com.netatmo.ylu.gofoot.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey


@Entity(
    tableName = "team_venue_cross_ref_table",
    primaryKeys = ["teamId", "venueId"],
    foreignKeys = [
        ForeignKey(
            entity = Team::class,
            parentColumns = ["teamId"],
            childColumns = ["teamId"],
            onDelete = ForeignKey.NO_ACTION
        ),
        ForeignKey(
            entity = Venue::class,
            parentColumns = ["venueId"],
            childColumns = ["venueId"],
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
data class TeamVenueCrossRef(
    @ColumnInfo(name = "teamId", index = true)
    val teamId: Int,
    @ColumnInfo(name = "venueId", index = true)
    val venueId: String
)