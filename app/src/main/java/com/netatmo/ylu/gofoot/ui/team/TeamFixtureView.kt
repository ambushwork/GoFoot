package com.netatmo.ylu.gofoot.ui.team

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.netatmo.ylu.gofoot.R

class TeamFixtureView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    init {
        inflate(context, R.layout.view_team_fixture, this)
    }
}