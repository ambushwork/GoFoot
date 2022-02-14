package com.netatmo.ylu.gofoot.ui.league

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.netatmo.ylu.gofoot.model.League

internal class LeaguesAdapter : RecyclerView.Adapter<LeaguesAdapter.LeagueHolder>() {

    var list: List<League> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var listener: Listener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueHolder {
        val itemView = LeagueItemView(parent.context)
        return LeagueHolder(itemView)
    }

    override fun onBindViewHolder(holder: LeagueHolder, position: Int) {
        holder.item.setData(list[position])
        holder.item.listener = object : LeagueItemView.Listener {
            override fun onItemClicked(id: String) {
                listener?.onItemClicked(id)
            }

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    internal class LeagueHolder(val item: LeagueItemView) : RecyclerView.ViewHolder(item)

    interface Listener {
        fun onItemClicked(id: String)
    }
}
