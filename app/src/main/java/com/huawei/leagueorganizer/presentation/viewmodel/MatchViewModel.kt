package com.huawei.leagueorganizer.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huawei.leagueorganizer.data.entity.TeamEntity
import com.huawei.leagueorganizer.data.repository.MatchRepository
import com.huawei.leagueorganizer.utils.FixtureHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MatchViewModel @Inject constructor(private val repository: MatchRepository) : ViewModel() {

    val matches = repository.matches

    fun generateFixture(teams: List<TeamEntity>) = viewModelScope.launch {
        repository.deleteMatches()

        val fixture = FixtureHelper.generateFixture(teams)

        fixture.forEach {
            repository.insertMatches(it)
        }
    }
}
