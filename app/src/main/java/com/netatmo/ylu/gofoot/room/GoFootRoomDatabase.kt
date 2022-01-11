package com.netatmo.ylu.gofoot.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.netatmo.ylu.gofoot.model.League
import com.netatmo.ylu.gofoot.model.Team
import com.netatmo.ylu.gofoot.model.TeamVenueCrossRef
import com.netatmo.ylu.gofoot.model.Venue
import com.netatmo.ylu.gofoot.room.league.LeagueDao

@Database(
    entities = [Team::class,
        Venue::class,
        TeamVenueCrossRef::class,
        League::class],
    version = 1,
    exportSchema = false
)
abstract class GoFootRoomDatabase : RoomDatabase() {

    abstract fun leagueDao(): LeagueDao

    abstract fun teamDao(): TeamDao

    companion object {

        @Volatile
        private var INSTANCE: GoFootRoomDatabase? = null

        fun getDatabase(context: Context): GoFootRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GoFootRoomDatabase::class.java,
                    "go_foot_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}