package com.huawei.leagueorganizer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.huawei.leagueorganizer.presentation.adapters.TeamAdapter
import com.huawei.leagueorganizer.presentation.viewmodel.MatchViewModel
import com.huawei.leagueorganizer.presentation.viewmodel.TeamViewModel
import com.huawei.leagueorganizer.utils.Preferences
import dagger.hilt.android.AndroidEntryPoint
import it.sephiroth.android.library.numberpicker.doOnProgressChanged
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val teamViewModel: TeamViewModel by viewModels()
    private val matchViewModel: MatchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        number_picker.setProgress(Preferences.getTeamCount(this))

        teamViewModel.teams.observe(this) { resource ->
            resource.data?.let { teamList ->
                rv_teams.adapter = TeamAdapter(teamList)
                val title = "${teamList.size} teams at Huawei Co. Ltd."
                tv_title.text = title
            }
            rv_teams.layoutManager =
                GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        }

        matchViewModel.matches.observe(this) {
            print(it)
        }

        fab.setOnClickListener {
            teamViewModel.teams.value?.data?.let { it1 -> matchViewModel.generateFixture(it1) }
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