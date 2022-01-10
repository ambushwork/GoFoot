package com.netatmo.ylu.gofoot.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.netatmo.ylu.gofoot.model.Team
import com.netatmo.ylu.gofoot.model.TeamInfo
import com.netatmo.ylu.gofoot.model.TeamVenueCrossRef
import com.netatmo.ylu.gofoot.model.Venue

@Dao
interface TeamDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeamAndVenue(teams: List<Team>, venues: List<Venue>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJoint(refs: List<TeamVenueCrossRef>)

    @Transaction
    @Query("SELECT * FROM team_table WHERE teamId = :teamId")
    suspend fun getTeamById(teamId: Int): TeamInfo

    @Transaction
    @Query("SELECT * FROM team_table ORDER BY name ASC")
    fun getTeamsLivedata(): LiveData<List<TeamInfo>>
}