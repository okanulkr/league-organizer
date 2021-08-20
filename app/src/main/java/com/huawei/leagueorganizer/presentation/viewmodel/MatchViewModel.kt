package com.huawei.leagueorganizer.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huawei.leagueorganizer.data.entity.MatchEntity
import com.huawei.leagueorganizer.data.repository.MatchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchViewModel @Inject constructor(private val repository: MatchRepository) : ViewModel() {

    val matches = repository.matches

    fun insertMatches(matches: List<MatchEntity>) = viewModelScope.launch {
        repository.insertMatches(matches)
    }

    fun deleteMatches() = viewModelScope.launch {
        repository.deleteMatches()
    }
}