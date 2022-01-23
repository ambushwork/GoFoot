package com.netatmo.ylu.gofoot.room.player

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.netatmo.ylu.gofoot.model.Player

@Dao
interface PlayerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPlayer(player: List<Player>)

    @Query("SELECT * FROM table_player WHERE teamId = :teamId")
    fun getPlayerByTeamId(teamId: Int): List<Player>

    @Query("SELECT * FROM table_player WHERE teamId = :teamId")
    fun getPlayerLiveDataByTeamId(teamId: Int): LiveData<List<Player>>

    @Query("SELECT * FROM table_player WHERE teamId = :teamId")
    fun getPlayerPagingDataByTeamId(teamId: Int): PagingSource<Int, Player>
}