package com.netatmo.ylu.gofoot.ui.player

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.netatmo.ylu.gofoot.ui.player.PlayerStatAdapter.StatHolder

class PlayerStatAdapter(val data: List<PlayerStatData>) : RecyclerView.Adapter<StatHolder>() {

    class StatHolder(val statisticItemView: StatisticItemView) :
        RecyclerView.ViewHolder(statisticItemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatHolder {
        return StatHolder(StatisticItemView(parent.context))
    }

    override fun onBindViewHolder(holder: StatHolder, position: Int) {
        data[position].apply {
            holder.statisticItemView.setViewModel(title, value)
        }
    }

    override fun getItemCount(): Int = data.size
}