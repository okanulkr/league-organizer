package com.huawei.leagueorganizer.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.huawei.leagueorganizer.R
import com.huawei.leagueorganizer.data.model.MatchDetailView
import com.huawei.leagueorganizer.presentation.adapters.MatchAdapter
import kotlinx.android.synthetic.main.fragment_week.view.*

class WeekFragment(private val week: Int, private val list: List<MatchDetailView>): Fragment(R.layout.fragment_week) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val  title = "Week $week"
        view.tv_title.text = title

        view.rv_matches.adapter = MatchAdapter(list)
        view.rv_matches.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }
}