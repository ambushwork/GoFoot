package com.netatmo.ylu.gofoot.ui.team

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.netatmo.ylu.gofoot.R
import com.netatmo.ylu.gofoot.model.Team
import com.squareup.picasso.Picasso

class TeamItemView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val tvName: TextView
    private val logoIv: ImageView
    private var team: Team? = null
        set(value) {
            value?.let {
                tvName.text = it.name
                Picasso.get().load(it.logo).into(logoIv)
            }
            field = value
        }

    init {
        inflate(context, R.layout.item_team, this)
        orientation = HORIZONTAL
        layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        weightSum = 1f
        gravity = Gravity.CENTER_VERTICAL
        tvName = findViewById(R.id.item_team_tv_name)
        logoIv = findViewById(R.id.item_team_iv_logo)
        setOnClickListener {
            team?.let {
                TeamInformationActivity.start(context, it.teamId)
            }
        }
    }

    fun setData(team: Team) {
        this.team = team
    }


}