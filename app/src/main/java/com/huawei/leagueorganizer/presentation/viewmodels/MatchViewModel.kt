package com.huawei.leagueorganizer.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huawei.leagueorganizer.data.model.MatchEntity
import com.huawei.leagueorganizer.data.model.TeamEntity
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

        val firstHalfFixture = FixtureHelper.generateFirstHalf(teams)

        val firstHalfMatches = arrayListOf<MatchEntity>()
        firstHalfFixture.forEach { matchList ->
            matchList.forEach { match ->
                firstHalfMatches.add(match!!)
            }
        }
        val secondHalf = FixtureHelper.generateSecondHalf(firstHalfFixture.size, firstHalfMatches)
        firstHalfMatches.addAll(secondHalf)
        repository.insertMatches(firstHalfMatches)
    }
}
