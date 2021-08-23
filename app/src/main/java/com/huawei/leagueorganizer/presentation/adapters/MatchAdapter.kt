package com.huawei.leagueorganizer.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.huawei.leagueorganizer.R
import com.huawei.leagueorganizer.data.model.MatchDetailView
import com.squareup.picasso.Picasso

class MatchAdapter(private val teamList: List<MatchDetailView>) :
    RecyclerView.Adapter<MatchAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val team1Name: TextView = view.findViewById(R.id.tv_team1_name)
        val team1Logo: ImageView = view.findViewById(R.id.iv_team1_logo)
        val team2Name: TextView = view.findViewById(R.id.tv_team2_name)
        val team2Logo: ImageView = view.findViewById(R.id.iv_team2_logo)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.match_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.team1Name.text = teamList[position].firstTeamName
        Picasso.get().load(teamList[position].firstTeamLogo).into(viewHolder.team1Logo)

        viewHolder.team2Name.text = teamList[position].secondTeamName
        Picasso.get().load(teamList[position].secondTeamLogo).into(viewHolder.team2Logo)
    }

    override fun getItemCount() = teamList.size
}