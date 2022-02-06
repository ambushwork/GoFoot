package com.netatmo.ylu.gofoot.ui.team

import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.recyclerview.widget.RecyclerView
import com.netatmo.ylu.gofoot.model.fixture.FixtureResponse

class TeamFixtureAdapter : RecyclerView.Adapter<TeamFixtureAdapter.FixtureHolder>() {

    companion object {
        private const val TYPE_FIXTURE = 1
        private const val TYPE_DATE = 2
    }

    var fixtures: List<FixtureResponse> = emptyList()
        set(value) {
            feed.fixtures = value
            notifyDataSetChanged()
            field = value
        }

    private val feed: TeamFixtureFeed by lazy { TeamFixtureFeed() }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FixtureHolder {
        val layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        return when (viewType) {
            TYPE_DATE -> FixtureHolder(FixtureDateView(parent.context).apply {
                this.layoutParams = layoutParams
            })
            TYPE_FIXTURE -> FixtureHolder(TeamFixtureItemView(parent.context).apply {
                this.layoutParams = layoutParams
            })
            else -> error("illegal view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: FixtureHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_DATE -> {
                holder.fixtureDateView?.text = feed.getDate(position).toString()
            }
            TYPE_FIXTURE -> {
                holder.teamFixtureItemView?.fixtureResponse = feed.getFixture(position)
            }
            else -> error("illegal view type in position $position")
        }
    }

    override fun getItemCount(): Int = feed.getSize()

    override fun getItemViewType(position: Int): Int {
        return when {
            feed.isDate(position) -> {
                TYPE_DATE
            }
            feed.isFixture(position) -> {
                TYPE_FIXTURE
            }
            else -> {
                error("unknown view type from position $position")
            }
        }
    }

    class FixtureHolder constructor(view: View) : RecyclerView.ViewHolder(view) {

        var teamFixtureItemView: TeamFixtureItemView? = null
        var fixtureDateView: FixtureDateView? = null

        constructor(itemView: TeamFixtureItemView) : this(itemView as View) {
            this.teamFixtureItemView = itemView
        }

        constructor(itemView: FixtureDateView) : this(itemView as View) {
            this.fixtureDateView = itemView
        }

    }


}