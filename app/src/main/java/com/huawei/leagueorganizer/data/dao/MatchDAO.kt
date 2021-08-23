package com.huawei.leagueorganizer.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.huawei.leagueorganizer.data.model.MatchDetailView
import com.huawei.leagueorganizer.data.model.MatchEntity

@Dao
interface MatchDAO {

    @Insert
    suspend fun insertMatches(teams: ArrayList<MatchEntity>)

    @Query("DELETE FROM MatchEntity")
    suspend fun deleteMatches()

    @Query("SELECT * FROM MatchDetailView")
    fun getAllMatches(): LiveData<List<MatchDetailView>>
}