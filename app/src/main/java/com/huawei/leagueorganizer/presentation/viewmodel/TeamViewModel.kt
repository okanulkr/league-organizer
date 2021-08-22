package com.huawei.leagueorganizer.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huawei.leagueorganizer.data.entity.TeamEntity
import com.huawei.leagueorganizer.data.repository.TeamRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamViewModel @Inject constructor(private val repository: TeamRepository) : ViewModel() {

    val teams = repository.teams

    fun deleteTeams() = viewModelScope.launch {
        repository.deleteTeams()
    }

    fun refresh() = viewModelScope.launch {
        repository.refresh().data?.let { insertTeams(it) }
    }

    private fun insertTeams(teams: List<TeamEntity>) = viewModelScope.launch {
        repository.insertTeams(teams)
    }
}