package com.huawei.leagueorganizer.data.datasource.remote

import com.huawei.leagueorganizer.data.model.TeamEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface TeamService {

    @GET("teams/{count}")
    @Headers(
        "Content-Type: application/json",
        "Accept: application/json"
    )
    suspend fun getAllTeams(@Path("count") count: Int) : Response<List<TeamEntity>>
}