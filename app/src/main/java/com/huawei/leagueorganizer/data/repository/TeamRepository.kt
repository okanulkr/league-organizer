package com.huawei.leagueorganizer.data.repository

import com.huawei.leagueorganizer.data.dao.TeamDAO
import com.huawei.leagueorganizer.data.entity.TeamEntity
import javax.inject.Inject

class TeamRepository @Inject constructor(private val dao: TeamDAO) {

    val teams = dao.getAllTeams()

    suspend fun insertTeams(teams: List<TeamEntity>) = dao.insertTeams(teams)

    suspend fun deleteTeams() = dao.deleteTeams()
}