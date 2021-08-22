package com.huawei.leagueorganizer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.huawei.leagueorganizer.presentation.adapters.TeamAdapter
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
        teamViewModel.teams.observe(this) { resource ->
            findViewById<RecyclerView>(R.id.rv_teams).adapter = resource.data?.let { teamList ->
                TeamAdapter(teamList)
            }
            findViewById<RecyclerView>(R.id.rv_teams).layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
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