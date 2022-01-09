package com.netatmo.ylu.gofoot.ui.team

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.netatmo.ylu.gofoot.model.TeamInfo

internal class TeamsAdapter : RecyclerView.Adapter<TeamsAdapter.TeamHolder>() {

    var list: List<TeamInfo> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamHolder {
        val itemView = TeamItemView(parent.context)
        return TeamHolder(itemView)
    }

    override fun onBindViewHolder(holder: TeamHolder, position: Int) {
        holder.item.setData(list[position].team)
    }

    override fun getItemCount(): Int = list.size


    internal class TeamHolder(val item: TeamItemView) : RecyclerView.ViewHolder(item)

}