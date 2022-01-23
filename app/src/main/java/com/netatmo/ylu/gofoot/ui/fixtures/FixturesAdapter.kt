package com.netatmo.ylu.gofoot.ui.fixtures

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.netatmo.ylu.gofoot.model.fixture.FixtureResponse

internal class FixturesAdapter : RecyclerView.Adapter<FixturesAdapter.FixtureHolder>() {

    var list: List<FixtureResponse> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var listener: Listener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FixtureHolder {
        val itemView = FixtureItemView(parent.context)
        return FixtureHolder(itemView)
    }

    override fun onBindViewHolder(holder: FixtureHolder, position: Int) {
        holder.item.setData(list[position])
        holder.item.listener = object : FixtureItemView.Listener {
            override fun onItemClicked(id: Int) {
                listener?.onItemClicked(id)
            }

        }
    }

    override fun getItemCount(): Int = list.size

    internal class FixtureHolder(val item: FixtureItemView) : RecyclerView.ViewHolder(item)

    interface Listener {
        fun onItemClicked(id: Int)
    }

}