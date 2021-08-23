package com.huawei.leagueorganizer.presentation.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.huawei.leagueorganizer.data.repository.TeamRepository
import com.huawei.leagueorganizer.utils.Preferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamViewModel @Inject constructor(private val repository: TeamRepository, application: Application) : AndroidViewModel(application) {

    val teams = repository.teams

    fun refresh(count: Int) = viewModelScope.launch {
        repository.deleteTeams()
        Preferences.setTeamCount(getApplication(), count)
        repository.getAllTeamsFromRemoteDatasource(count).data?.let { repository.insertTeams(it) }
    }
}