package com.huawei.leagueorganizer.data.entity

import androidx.room.*


@DatabaseView("""
    SELECT      (SELECT TeamEntity.name FROM TeamEntity WHERE TeamEntity.id = MatchEntity.firstTeamId) AS firstTeamName, 
                (SELECT TeamEntity.logo FROM TeamEntity WHERE TeamEntity.id = MatchEntity.firstTeamId) AS firstTeamLogo, 
                (SELECT TeamEntity.name FROM TeamEntity WHERE TeamEntity.id = MatchEntity.secondTeamId) AS secondTeamName, 
                (SELECT TeamEntity.logo FROM TeamEntity WHERE TeamEntity.id = MatchEntity.secondTeamId) AS secondTeamLogo, 
                MatchEntity.week 
    FROM MatchEntity""")
data class MatchDetailView(
    val firstTeamName: String,
    val firstTeamLogo: String,
    val secondTeamName: String,
    val secondTeamLogo: String,
    val week: Int
)
