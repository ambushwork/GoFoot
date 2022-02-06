package com.netatmo.ylu.gofoot.ui.team

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.netatmo.ylu.gofoot.R
import com.netatmo.ylu.gofoot.model.fixture.FixtureResponse
import com.squareup.picasso.Picasso

class TeamFixtureItemView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private val homeIcon: ImageView
    private val awayIcon: ImageView

    var fixtureResponse: FixtureResponse? = null
        set(value) {
            value?.let {
                Picasso.get().load(it.fixtureTeams.home.logo).into(homeIcon)
                Picasso.get().load(it.fixtureTeams.away.logo).into(awayIcon)
            }

            field = value
        }

    init {
        inflate(context, R.layout.item_team_fixture_view, this)
        homeIcon = findViewById(R.id.ic_team_home)
        awayIcon = findViewById(R.id.ic_team_away)
    }
}