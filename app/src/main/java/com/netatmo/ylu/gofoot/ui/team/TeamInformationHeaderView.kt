package com.netatmo.ylu.gofoot.ui.team

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.netatmo.ylu.gofoot.R
import com.netatmo.ylu.gofoot.model.TeamInfo
import com.squareup.picasso.Picasso

class TeamInformationHeaderView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private val logoIv: ImageView
    private val nameTv: TextView
    private val venueTv: TextView

    init {
        inflate(context, R.layout.view_team_info_header, this)
        logoIv = findViewById(R.id.view_team_info_header_logo_iv)
        nameTv = findViewById(R.id.view_team_info_header_name_tv)
        venueTv = findViewById(R.id.view_team_info_header_venue_tv)
    }

    fun setViewModel(teamInfo: TeamInfo) {
        Picasso.get().load(teamInfo.team.logo).into(logoIv)
        nameTv.text = teamInfo.team.name
    }
}