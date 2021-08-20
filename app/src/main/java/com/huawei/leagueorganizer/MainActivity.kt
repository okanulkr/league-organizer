package com.huawei.leagueorganizer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import com.huawei.leagueorganizer.data.entity.TeamEntity
import com.huawei.leagueorganizer.presentation.viewmodel.TeamViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val teamViewModel: TeamViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        teamViewModel.teams.observeForever {
            print("teams")
        }

        findViewById<TextView>(R.id.tv).setOnClickListener {
            teamViewModel.insertTeams(listOf(TeamEntity(0, "GS", "GSLogo")))
        }

        findViewById<TextView>(R.id.tv).setOnLongClickListener {
            teamViewModel.deleteTeams()
            true
        }
    }
}