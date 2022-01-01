package com.netatmo.ylu.gofoot.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.netatmo.ylu.gofoot.model.League

internal class LeaguesAdapter : RecyclerView.Adapter<LeaguesAdapter.LeagueHolder>() {
    
    var list: List<League> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueHolder {
        val itemView = LeagueItemView(parent.context)
        return LeagueHolder(itemView)
    }

    override fun onBindViewHolder(holder: LeagueHolder, position: Int) {
        holder.item.setData(list[position])
    }

    override fun getItemCount(): Int = list.size


    internal class LeagueHolder(val item: LeagueItemView) : RecyclerView.ViewHolder(item)

}