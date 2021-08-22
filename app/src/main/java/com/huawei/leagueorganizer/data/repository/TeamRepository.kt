package com.huawei.leagueorganizer.data.repository

import com.huawei.leagueorganizer.data.dao.TeamDAO
import com.huawei.leagueorganizer.data.datasource.remote.TeamRemoteDatasource
import com.huawei.leagueorganizer.data.entity.TeamEntity
import com.huawei.leagueorganizer.data.datasource.performGetOperation
import com.huawei.leagueorganizer.utils.Constants
import javax.inject.Inject

class TeamRepository @Inject constructor(
    private val dao: TeamDAO,
    private val remoteDatasource: TeamRemoteDatasource
) {

    val teams = performGetOperation(
        databaseQuery = { dao.getAllTeams() },
        networkCall = { getAllTeamsFromRemoteDatasource(Constants.TEAM_COUNT) },
        saveCallResult = { insertTeams(it) }
    )

    suspend fun getAllTeamsFromRemoteDatasource(count: Int) =
        remoteDatasource.getAllTeams(count)

    suspend fun insertTeams(teams: List<TeamEntity>) = dao.insertTeams(teams)

    suspend fun deleteTeams() = dao.deleteTeams()
}