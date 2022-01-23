package com.netatmo.ylu.gofoot.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.netatmo.ylu.gofoot.model.RemoteKey

@Dao
interface RemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(remoteKey: RemoteKey)

    @Query("SELECT * FROM table_remote_keys WHERE teamId = :teamId")
    suspend fun remoteKeyByQuery(teamId: Int): RemoteKey

    @Query("DELETE FROM table_remote_keys WHERE teamId = :teamId")
    suspend fun deleteByQuery(teamId: Int)

}