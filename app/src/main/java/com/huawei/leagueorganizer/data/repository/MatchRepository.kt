package com.huawei.leagueorganizer.data.repository

import com.huawei.leagueorganizer.data.dao.MatchDAO
import com.huawei.leagueorganizer.data.entity.MatchEntity
import javax.inject.Inject

class MatchRepository @Inject constructor(private val dao: MatchDAO) {

    val matches = dao.getAllMatches()

    suspend fun insertMatches(matches: ArrayList<MatchEntity>) = dao.insertMatches(matches)

    suspend fun deleteMatches() = dao.deleteMatches()
}