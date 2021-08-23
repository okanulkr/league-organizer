package com.huawei.leagueorganizer.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.huawei.leagueorganizer.R
import com.huawei.leagueorganizer.data.model.TeamEntity
import com.squareup.picasso.Picasso

class TeamAdapter(private val teamList: List<TeamEntity>) :
    RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.tv_team_name)
        val imageView: ImageView = view.findViewById(R.id.iv_team_logo)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.team_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.textView.text = teamList[position].name
        Picasso.get().load(teamList[position].logo).into(viewHolder.imageView)
    }

    override fun getItemCount() = teamList.size
}