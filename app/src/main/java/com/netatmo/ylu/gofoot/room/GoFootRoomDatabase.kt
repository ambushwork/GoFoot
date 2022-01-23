package com.netatmo.ylu.gofoot.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.netatmo.ylu.gofoot.model.*
import com.netatmo.ylu.gofoot.room.league.LeagueDao
import com.netatmo.ylu.gofoot.room.player.PlayerDao

@Database(
    entities = [Team::class,
        Venue::class,
        TeamVenueCrossRef::class,
        League::class,
        Player::class,
        RemoteKey::class],
    version = 1,
    exportSchema = false
)
abstract class GoFootRoomDatabase : RoomDatabase() {

    abstract fun leagueDao(): LeagueDao

    abstract fun teamDao(): TeamDao

    abstract fun playerDao(): PlayerDao

    abstract fun remoteKeyDao(): RemoteKeyDao

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