package com.huawei.leagueorganizer.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.huawei.leagueorganizer.R
import com.huawei.leagueorganizer.presentation.adapters.TeamAdapter
import com.huawei.leagueorganizer.presentation.viewmodels.MatchViewModel
import com.huawei.leagueorganizer.presentation.viewmodels.TeamViewModel
import com.huawei.leagueorganizer.utils.Preferences
import com.skydoves.transformationlayout.TransformationLayout
import dagger.hilt.android.AndroidEntryPoint
import it.sephiroth.android.library.numberpicker.doOnProgressChanged
import kotlinx.android.synthetic.main.fragment_team.*

@AndroidEntryPoint
class TeamFragment : Fragment(R.layout.fragment_team) {

    private val teamViewModel: TeamViewModel by viewModels()
    private val matchViewModel: MatchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.run {
            observeTeamData(this)
            handleTransition()
            listenNumberPicker(context, this)
            refreshOnFirstLaunch(this)
        }
    }

    private fun observeTeamData(view: View) {
        view.run {
            teamViewModel.teams.observe(viewLifecycleOwner) { resource ->
                resource.data?.let { teamList ->
                    val title = "${teamList.size} teams at Huawei Co. Ltd."
                    tv_title.text = title
                    rv_teams.adapter = TeamAdapter(teamList)
                }
                rv_teams.layoutManager =
                    GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            }
        }
    }

    private fun refreshOnFirstLaunch(view: View) {
        view.run {
            if (Preferences.isFirstRun(requireContext())) {
                Preferences.setFirstRun(requireContext(), false)
                teamViewModel.refresh(number_picker.progress)
            }
        }
    }

    private fun handleTransition() {
        requireActivity().findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            requireActivity().findViewById<TransformationLayout>(R.id.transformationLayout)
                .startTransform()
            teamViewModel.teams.value?.data?.let { it1 -> matchViewModel.generateFixture(it1) }
            findNavController().navigate(TeamFragmentDirections.actionTeamFragmentToFixtureFragment())
        }
    }

    private fun listenNumberPicker(context: Context, view: View) {
        view.run {
            number_picker.setProgress(Preferences.getTeamCount(context))
            number_picker.doOnProgressChanged { numberPicker, progress, _ ->

                if (progress % 2 != 0) {
                    Snackbar.make(
                        this,
                        "Count must be even",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    numberPicker.setProgress(progress + 1)
                } else {
                    teamViewModel.refresh(progress)
                }
            }
        }
    }
}
