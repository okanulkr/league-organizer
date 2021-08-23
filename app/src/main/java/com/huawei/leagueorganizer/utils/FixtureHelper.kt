package com.huawei.leagueorganizer.utils

import com.huawei.leagueorganizer.data.entity.MatchEntity
import com.huawei.leagueorganizer.data.entity.TeamEntity

object FixtureHelper {

    /**
     * Cyclic algorithm used in function below
     */
    fun generateFixture(teamList: List<TeamEntity>): Array<Array<MatchEntity?>> {
        var teams = teamList.size

        if (teams % 2 == 1) {
            teams++
        }

        val totalWeeks = teams - 1
        val matchesPerWeek = teams / 2
        var rounds =
            Array(totalWeeks) {     // simple 2-D array init. in kotlin, equivalent of string[][] in java
                arrayOfNulls<MatchEntity>(
                    matchesPerWeek
                )
            }

        for (week in 0 until totalWeeks) {
            for (match in 0 until matchesPerWeek) {
                val home = (week + match) % (teams - 1)
                var away = (teams - 1 - match + week) % (teams - 1)
                if (match == 0) {   // one team stays still while others rotate around
                    away = teams - 1
                }
                rounds[week][match] = MatchEntity(0, teamList[home].id, teamList[away].id, week)
            }
        }

        val shuffled = Array(totalWeeks) {
            arrayOfNulls<MatchEntity>(
                matchesPerWeek
            )
        }

        var evn = 0
        var odd = teams / 2
        for (i in rounds.indices) {
            if (i % 2 == 0) {
                shuffled[i] = rounds[evn++]
            } else {
                shuffled[i] = rounds[odd++]
            }
        }

        rounds = shuffled

        for (round in rounds.indices) {
            if (round % 2 == 1) {
                rounds[round][0] = flip(rounds[round][0]!!)
            }
        }

        return rounds
    }

    private fun flip(match: MatchEntity): MatchEntity =
        MatchEntity(match.id, match.secondTeamId, match.firstTeamId, match.week)
}