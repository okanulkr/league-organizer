package com.huawei.leagueorganizer.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.huawei.leagueorganizer.data.dao.MatchDAO
import com.huawei.leagueorganizer.data.dao.TeamDAO
import com.huawei.leagueorganizer.data.entity.MatchDetailView
import com.huawei.leagueorganizer.data.entity.MatchEntity
import com.huawei.leagueorganizer.data.entity.TeamEntity

@Database(
    entities = [TeamEntity::class, MatchEntity::class],
    views = [MatchDetailView::class],
    version = 1
)
abstract class LeagueOrganizerDatabase : RoomDatabase() {

    abstract val teamDAO: TeamDAO
    abstract val matchDAO: MatchDAO
}

