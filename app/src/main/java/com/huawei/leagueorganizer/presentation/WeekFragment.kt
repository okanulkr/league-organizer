package com.huawei.leagueorganizer.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.huawei.leagueorganizer.R
import com.huawei.leagueorganizer.data.entity.MatchDetailView
import kotlinx.android.synthetic.main.fragment_week.view.*

class WeekFragment(private val week: Int, private val list: List<MatchDetailView>): Fragment(R.layout.fragment_week) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.tv_title.text = ""

        list.forEach {
            view.tv_title.append(it.firstTeamName + " " + it.secondTeamName + "\n")
        }
    }
}