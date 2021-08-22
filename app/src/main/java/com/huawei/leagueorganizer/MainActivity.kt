package com.huawei.leagueorganizer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import com.huawei.leagueorganizer.presentation.viewmodel.MatchViewModel
import com.huawei.leagueorganizer.presentation.viewmodel.TeamViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val teamViewModel: TeamViewModel by viewModels()
    private val matchViewModel: MatchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        teamViewModel.deleteTeams()
        teamViewModel.teams.observe(this) {
            findViewById<TextView>(R.id.tv).text = ""
            it.data?.forEach { te ->
                findViewById<TextView>(R.id.tv).append(te.name + "\n")
            }
        }

        matchViewModel.matches.observe(this) {
            print(it)
        }

        findViewById<TextView>(R.id.tv).setOnClickListener {
            teamViewModel.deleteTeams()
            teamViewModel.refresh()
        }

        findViewById<TextView>(R.id.tv).setOnLongClickListener {
            teamViewModel.teams.value?.data?.let { it1 -> matchViewModel.generateFixture(it1) }
            true
        }
    }
}