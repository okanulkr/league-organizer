package com.huawei.leagueorganizer.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.huawei.leagueorganizer.R
import com.huawei.leagueorganizer.presentation.adapters.TeamAdapter
import com.huawei.leagueorganizer.presentation.viewmodel.MatchViewModel
import com.huawei.leagueorganizer.presentation.viewmodel.TeamViewModel
import com.huawei.leagueorganizer.utils.Preferences
import dagger.hilt.android.AndroidEntryPoint
import it.sephiroth.android.library.numberpicker.doOnProgressChanged
import kotlinx.android.synthetic.main.fragment_team.view.*

@AndroidEntryPoint
class TeamFragment : Fragment(R.layout.fragment_team) {

    private val teamViewModel: TeamViewModel by viewModels()
    private val matchViewModel: MatchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.run {

            number_picker.setProgress(Preferences.getTeamCount(context))

            teamViewModel.teams.observe(viewLifecycleOwner) { resource ->
                resource.data?.let { teamList ->
                    val title = "${teamList.size} teams at Huawei Co. Ltd."
                    tv_title.text = title
                    rv_teams.adapter = TeamAdapter(teamList)
                }
                rv_teams.layoutManager =
                    GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            }

            matchViewModel.matches.observe(viewLifecycleOwner) {
                print(it)
            }

            fab.setOnClickListener {
                teamViewModel.teams.value?.data?.let { it1 -> matchViewModel.generateFixture(it1) }
                findNavController().navigate(TeamFragmentDirections.actionTeamFragmentToFixtureFragment())
            }

            number_picker.doOnProgressChanged { numberPicker, progress, _ ->

                if (progress % 2 != 0) {
                    Snackbar.make(
                        findViewById(android.R.id.content),
                        "Count must be even",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    numberPicker.setProgress(progress + 1)
                } else {
                    teamViewModel.deleteTeams()
                    teamViewModel.refresh(progress)
                }
            }
        }
    }
}
