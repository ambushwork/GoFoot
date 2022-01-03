package com.netatmo.ylu.gofoot.ui.league

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
import com.netatmo.ylu.gofoot.model.League
import com.squareup.picasso.Picasso

class LeagueItemView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val tvName: TextView
    private val logoIv: ImageView
    var listener: Listener? = null
    private var league: League? = null

    init {
        inflate(context, R.layout.item_league, this)
        orientation = HORIZONTAL
        layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        weightSum = 1f
        gravity = Gravity.CENTER_VERTICAL
        tvName = findViewById(R.id.item_league_tv_name)
        logoIv = findViewById(R.id.item_league_iv_logo)
        setOnClickListener {
            league?.let {
                listener?.onItemClicked(it.id)
            }
        }
    }

    fun setData(league: League) {
        this.league = league
        tvName.text = league.name
        Picasso.get().load(league.logo).into(logoIv)
    }

    interface Listener {
        fun onItemClicked(id: String)
    }

}