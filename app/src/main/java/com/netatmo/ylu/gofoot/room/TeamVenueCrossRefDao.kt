package com.netatmo.ylu.gofoot.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.netatmo.ylu.gofoot.model.TeamVenueCrossRef

@Dao
interface TeamVenueCrossRefDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(join: TeamVenueCrossRef)
}