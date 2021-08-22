package com.huawei.leagueorganizer.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.huawei.leagueorganizer.R
import com.huawei.leagueorganizer.presentation.viewmodel.MatchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FixtureFragment : Fragment(R.layout.fragment_fixture) {

    private val matchViewModel: MatchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        matchViewModel.matches.observe(viewLifecycleOwner) { matchList ->
            val grouped = matchList.groupBy {
                it.week
            }
            print(grouped)
        }
    }
}
