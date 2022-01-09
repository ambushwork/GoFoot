package com.netatmo.ylu.gofoot.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.netatmo.ylu.gofoot.model.Team
import com.netatmo.ylu.gofoot.model.TeamInfo
import com.netatmo.ylu.gofoot.model.Venue

@Dao
interface TeamDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeam(teams: List<Team>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVenue(venue: List<Venue>)

    @Transaction
    @Query("SELECT * FROM team_table WHERE team_id = :teamId")
    suspend fun getTeamById(teamId: Int): TeamInfo

    @Transaction
    @Query("SELECT * FROM team_table ORDER BY team_name ASC")
    fun getTeamsLivedata(): LiveData<List<TeamInfo>>
}