package com.netatmo.ylu.gofoot.ui.fixtures

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.setPadding
import com.netatmo.ylu.gofoot.R
import com.netatmo.ylu.gofoot.model.fixture.FixtureResponse
import com.squareup.picasso.Picasso

class FixtureItemView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private val tvGoals: TextView
    private val homeIcon: ImageView
    private val awayIcon: ImageView
    private val tvHomeName: TextView
    private val tvAwayName: TextView
    private val tvTime: TextView
    private val tvLeague: TextView
    var listener: Listener? = null
    private var fixture: FixtureResponse? = null

    init {
        inflate(context, R.layout.item_fixture, this)
        layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        tvGoals = findViewById(R.id.item_fixture_goals_tv)
        homeIcon = findViewById(R.id.item_fixture_home_iv)
        awayIcon = findViewById(R.id.item_fixture_away_iv)
        tvAwayName = findViewById(R.id.item_fixture_away_name_tv)
        tvHomeName = findViewById(R.id.item_fixture_home_name_tv)
        tvTime = findViewById(R.id.item_fixture_time_tv)
        tvLeague = findViewById(R.id.item_fixture_league_tv)
        setPadding(16)
        setOnClickListener {
            fixture?.let {
                listener?.onItemClicked(it.fixture.id)
            }
        }
    }

    fun setData(fixture: FixtureResponse) {
        this.fixture = fixture
        tvGoals.text = String.format("${fixture.goals.home} - ${fixture.goals.away}")
        Picasso.get().load(fixture.fixtureTeams.away.logo).into(awayIcon)
        Picasso.get().load(fixture.fixtureTeams.home.logo).into(homeIcon)
        tvHomeName.text = fixture.fixtureTeams.home.name
        tvAwayName.text = fixture.fixtureTeams.away.name
        tvTime.text = fixture.fixture.status.elapsed.toString()
        tvLeague.text = fixture.league.name
    }

    interface Listener {
        fun onItemClicked(id: Int)
    }

}