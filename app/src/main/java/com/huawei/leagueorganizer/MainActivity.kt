package com.huawei.leagueorganizer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.huawei.leagueorganizer.presentation.adapters.TeamAdapter
import com.huawei.leagueorganizer.presentation.viewmodel.MatchViewModel
import com.huawei.leagueorganizer.presentation.viewmodel.TeamViewModel
import com.huawei.leagueorganizer.utils.Constants
import com.huawei.leagueorganizer.utils.Preferences
import dagger.hilt.android.AndroidEntryPoint
import it.sephiroth.android.library.numberpicker.NumberPicker
import it.sephiroth.android.library.numberpicker.doOnProgressChanged

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val teamViewModel: TeamViewModel by viewModels()
    private val matchViewModel: MatchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<NumberPicker>(R.id.number_picker).setProgress(Preferences.getTeamCount(this))

//        teamViewModel.deleteTeams()
        teamViewModel.teams.observe(this) { resource ->
            findViewById<RecyclerView>(R.id.rv_teams).adapter = resource.data?.let { teamList ->
                TeamAdapter(teamList)
            }
            findViewById<RecyclerView>(R.id.rv_teams).layoutManager =
                GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        }

        matchViewModel.matches.observe(this) {
            print(it)
        }

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            teamViewModel.teams.value?.data?.let { it1 -> matchViewModel.generateFixture(it1) }
        }

        findViewById<NumberPicker>(R.id.number_picker).doOnProgressChanged { numberPicker, progress, formUser ->

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