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
import com.netatmo.ylu.gofoot.model.FixtureResponse
import com.squareup.picasso.Picasso

class FixtureItemView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private val tvGoals: TextView
    private val homeIcon: ImageView
    private val awayIcon: ImageView
    var listener: Listener? = null
    private var fixture: FixtureResponse? = null

    init {
        inflate(context, R.layout.item_fixture, this)
        layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        tvGoals = findViewById(R.id.item_fixture_goals_tv)
        homeIcon = findViewById(R.id.item_fixture_home_iv)
        awayIcon = findViewById(R.id.item_fixture_away_iv)
        setPadding(16)
        setOnClickListener {
            fixture?.let {
                listener?.onItemClicked(it.fixtures.id)
            }
        }
    }

    fun setData(fixture: FixtureResponse) {
        this.fixture = fixture
        tvGoals.text = String.format("${fixture.goals.home} - ${fixture.goals.away}")
        Picasso.get().load(fixture.fixtureTeams.away.logo).into(awayIcon)
        Picasso.get().load(fixture.fixtureTeams.home.logo).into(homeIcon)
    }

    interface Listener {
        fun onItemClicked(id: Int)
    }

}