package com.huawei.leagueorganizer.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.huawei.leagueorganizer.data.model.TeamEntity

@Dao
interface TeamDAO {

    @Insert
    suspend fun insertTeams(teams: List<TeamEntity>)

    @Query("DELETE FROM TeamEntity")
    suspend fun deleteTeams()

    @Query("SELECT * FROM TeamEntity")
    fun getAllTeams(): LiveData<List<TeamEntity>>
}