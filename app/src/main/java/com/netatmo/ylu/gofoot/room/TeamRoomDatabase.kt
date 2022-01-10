package com.netatmo.ylu.gofoot.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.netatmo.ylu.gofoot.model.Team
import com.netatmo.ylu.gofoot.model.TeamVenueCrossRef
import com.netatmo.ylu.gofoot.model.Venue

@Database(
    entities = [Team::class, Venue::class, TeamVenueCrossRef::class],
    version = 1,
    exportSchema = false
)
abstract class TeamRoomDatabase : RoomDatabase() {

    abstract fun teamDao(): TeamDao

    companion object {

        @Volatile
        private var INSTANCE: TeamRoomDatabase? = null

        fun getDatabase(context: Context): TeamRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TeamRoomDatabase::class.java,
                    "team_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}