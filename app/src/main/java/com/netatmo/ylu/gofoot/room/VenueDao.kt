package com.netatmo.ylu.gofoot.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.netatmo.ylu.gofoot.model.Venue

@Dao
interface VenueDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(venue: Venue)
}