package com.netatmo.ylu.gofoot.ui.team

import androidx.annotation.StringRes
import com.netatmo.ylu.gofoot.R

enum class TeamPage(
    val position: Int,
    @StringRes val title: Int
) {

    FIXTURES(0, R.string.__PAGE_TITLE_FIXTURE),

    PLAYERS(1, R.string.__PAGE_TITLE_PLAYER);

    companion object {
        fun fromPosition(position: Int): TeamPage {
            return values().firstOrNull { it.position == position }
                ?: error("unknown position: $position")
        }
    }
}