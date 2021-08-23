package com.huawei.leagueorganizer.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huawei.leagueorganizer.data.entity.MatchEntity
import com.huawei.leagueorganizer.data.entity.TeamEntity
import com.huawei.leagueorganizer.data.repository.MatchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MatchViewModel @Inject constructor(private val repository: MatchRepository) : ViewModel() {

    val matches = repository.matches

    fun generateFixture(teams: List<TeamEntity>) = viewModelScope.launch {
        repository.deleteMatches()

        val fixture = mutableListOf<MatchEntity>()

        generateFixtureA(teams)
        return@launch

        teams.forEachIndexed { index1, team1 ->
            var week = 1
            teams.filter { team2 -> team2.id != team1.id }.forEachIndexed { index2, team2 ->
                if (!isFirstTeamAvailable(fixture, team1, week)) {
                    week++
                } else if (isSecondTeamAvailable(fixture, team2, week)) {
                    fixture.add(
                        if (index2 % 2 == 0) MatchEntity(0, team1.id, team2.id, week++)
                        else MatchEntity(0, team2.id, team1.id, week++)
                    )
                }
            }
        }

        repository.insertMatches(fixture)

        /*val second = fixture.map {
            MatchEntity(it.id, it.secondTeamId, it.firstTeamId, it.week + fixture.size / 2)
        }

        repository.insertMatches(second)*/
    }

    fun insertMatches(matches: List<MatchEntity>) = viewModelScope.launch {
        repository.insertMatches(matches)
    }

    fun deleteMatches() = viewModelScope.launch {
        repository.deleteMatches()
    }

    private fun isFirstTeamAvailable(
        fixture: List<MatchEntity>,
        team1: TeamEntity,
        week: Int
    ): Boolean {
        return fixture.none {
            it.week == week && (team1.id == it.firstTeamId || team1.id == it.secondTeamId)
        }
    }

    private fun isSecondTeamAvailable(
        fixture: List<MatchEntity>,
        team2: TeamEntity,
        week: Int
    ): Boolean {
        return fixture.none {
            it.week == week && (team2.id == it.firstTeamId || team2.id == it.secondTeamId)
        }
    }

    private fun generateFixtureA(teamList: List<TeamEntity>) {
        var teams = teamList.size

        var ghost = false
        if (teams % 2 == 1) {
            teams++
            ghost = true
        }

        // Generate the fixtures using the cyclic algorithm.
        val totalRounds = teams - 1
        val matchesPerRound = teams / 2
        var rounds = Array(totalRounds) {
            arrayOfNulls<String>(
                matchesPerRound
            )
        }

        for (round in 0 until totalRounds) {
            for (match in 0 until matchesPerRound) {
                val home = (round + match) % (teams - 1)
                var away = (teams - 1 - match + round) % (teams - 1)
                // Last team stays in the same place while the others
                // rotate around it.
                if (match == 0) {
                    away = teams - 1
                }
                // Add one so teams are number 1 to teams not 0 to teams - 1
                // upon display.
                rounds[round][match] = teamList[home].name + " v " + (teamList[away].name)
            }
        }

        // Interleave so that home and away games are fairly evenly dispersed.
        val interleaved = Array(totalRounds) {
            arrayOfNulls<String>(
                matchesPerRound
            )
        }

        var evn = 0
        var odd = teams / 2
        for (i in rounds.indices) {
            if (i % 2 == 0) {
                interleaved[i] = rounds[evn++]
            } else {
                interleaved[i] = rounds[odd++]
            }
        }

        rounds = interleaved

        // Last team can't be away for every game so flip them
        // to home on odd rounds.

        // Last team can't be away for every game so flip them
        // to home on odd rounds.
        for (round in rounds.indices) {
            if (round % 2 == 1) {
                rounds[round][0] = flip(rounds[round][0]!!)
            }
        }

        // Display the fixtures

        // Display the fixtures
        for (i in rounds.indices) {
            println("Round " + (i + 1))
            println(listOf(rounds[i]))
            println()
        }

        println()

        if (ghost) {
            println("Matches against team $teams are byes.")
        }

        println(
            "Use mirror image of these rounds for "
                    + "return fixtures."
        )
    }

    private fun flip(match: String): String {
        val components = match.split(" v ").toTypedArray()
        return components[1] + " v " + components[0]
    }
}
