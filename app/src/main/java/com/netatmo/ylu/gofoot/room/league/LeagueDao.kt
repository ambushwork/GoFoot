package com.netatmo.ylu.gofoot.room.league

import androidx.lifecycle.LiveData
import androidx.room.*
import com.netatmo.ylu.gofoot.model.League
import com.netatmo.ylu.gofoot.model.LeagueType

@Dao
interface LeagueDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLeagues(leagues: List<League>)

    @Transaction
    @Query("SELECT * FROM table_leagues WHERE type = :type")
    fun getLeaguesLiveData(type: LeagueType): LiveData<List<League>>

    @Transaction
    @Query("SELECT * FROM table_leagues")
    fun getAllLeaguesLiveData(): LiveData<List<League>>

    @Query("SELECT * FROM table_leagues")
    fun getAllLeagues(): List<League>

}