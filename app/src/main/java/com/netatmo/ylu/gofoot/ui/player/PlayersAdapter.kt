package com.netatmo.ylu.gofoot.ui.player

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.netatmo.ylu.gofoot.model.Player

internal class PlayersAdapter : RecyclerView.Adapter<PlayersAdapter.PlayerHolder>() {

    internal class PlayerHolder(val item: PlayerItemView) : RecyclerView.ViewHolder(item)

    var list: List<Player> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerHolder {
        return PlayerHolder(PlayerItemView(parent.context))
    }

    override fun onBindViewHolder(holder: PlayerHolder, position: Int) {
        holder.item.player = list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }
}