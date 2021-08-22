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

        teams.forEach { team1 ->
            teams.filter { team2 -> team2.id != team1.id }.forEachIndexed { index, team2 ->
                fixture.add(
                    if (index % 2 == 0) MatchEntity(0, team1.id, team2.id, index + 1)
                    else MatchEntity(0, team2.id, team1.id, index + 1)
                )
            }
        }

        repository.insertMatches(fixture)
    }

    fun insertMatches(matches: List<MatchEntity>) = viewModelScope.launch {
        repository.insertMatches(matches)
    }

    fun deleteMatches() = viewModelScope.launch {
        repository.deleteMatches()
    }
}