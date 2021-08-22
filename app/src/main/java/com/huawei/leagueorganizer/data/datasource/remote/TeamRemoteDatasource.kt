package com.huawei.leagueorganizer.data.datasource.remote

import com.huawei.leagueorganizer.data.datasource.BaseDataSource
import javax.inject.Inject

class TeamRemoteDatasource @Inject constructor(
    private val teamService: TeamService
): BaseDataSource() {

    suspend fun getAllTeams(count: Int) = getResult { teamService.getAllTeams(count) }
}