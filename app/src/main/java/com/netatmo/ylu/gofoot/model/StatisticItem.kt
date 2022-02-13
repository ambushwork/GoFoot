package com.netatmo.ylu.gofoot.model

import androidx.annotation.StringRes
import com.netatmo.ylu.gofoot.R

enum class StatisticItem(@StringRes val title: Int) {

    GAME_APPEARANCE(R.string.__STAT_TITLE_GAMES),

    SHOT(R.string.__STAT_TITLE_GOALS),

    ASSIST(R.string.__STAT_TITLE_ASSIST);
}
