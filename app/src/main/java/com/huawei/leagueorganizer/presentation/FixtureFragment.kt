package com.huawei.leagueorganizer.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.huawei.leagueorganizer.R
import com.huawei.leagueorganizer.presentation.adapters.ViewPagerAdapter
import com.huawei.leagueorganizer.presentation.viewmodel.MatchViewModel
import com.skydoves.transformationlayout.TransformationLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_fixture.*

@AndroidEntryPoint
class FixtureFragment : Fragment(R.layout.fragment_fixture) {

    private val matchViewModel: MatchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().findViewById<FloatingActionButton>(R.id.test_icon).setOnClickListener {
            requireActivity().findViewById<TransformationLayout>(R.id.transformationLayout).finishTransform()
            findNavController().navigate(FixtureFragmentDirections.actionFixtureFragmentToTeamFragment())
        }

        matchViewModel.matches.observe(viewLifecycleOwner) { matchList ->
            val grouped = matchList.sortedBy { it.week }.groupBy {
                it.week
            }

            val fragments = mutableListOf<Fragment>()

            grouped.forEach { (t, u) ->
                fragments.add(WeekFragment(t + 1, u))
            }

            val adapter = ViewPagerAdapter(fragments, requireActivity().supportFragmentManager, lifecycle)

            view_pager.adapter = adapter
        }
    }
}
