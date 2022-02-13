package com.netatmo.ylu.gofoot.ui.player

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.netatmo.ylu.gofoot.model.Player

internal class PlayersAdapter :
    PagingDataAdapter<Player, PlayersAdapter.PlayerHolder>(DiffUtilCallback) {

    var listener: Listener? = null

    internal class PlayerHolder(val item: PlayerItemView) : RecyclerView.ViewHolder(item)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerHolder {
        return PlayerHolder(PlayerItemView(parent.context))
    }

    override fun onBindViewHolder(holder: PlayerHolder, position: Int) {
        getItem(position)?.let {
            holder.item.player = it
            holder.item.listener = object : PlayerItemView.Listener {
                override fun onPlayerDetailRequested(playerId: Int) {
                    listener?.onPlayerDetailRequested(playerId)
                }

            }
        }
    }

    object DiffUtilCallback : DiffUtil.ItemCallback<Player>() {
        override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
            return oldItem == newItem
        }

    }

    interface Listener {

        fun onPlayerDetailRequested(playerId: Int)
    }
}

