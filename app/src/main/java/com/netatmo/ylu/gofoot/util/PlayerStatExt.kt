package com.netatmo.ylu.gofoot.util

import com.netatmo.ylu.gofoot.model.PlayerResponse
import com.netatmo.ylu.gofoot.model.StatisticItem
import com.netatmo.ylu.gofoot.ui.player.PlayerStatData

fun PlayerResponse.getTotalAppearance(): Int {
    return statistics.sumOf {
        it.games.appearances
    }
}

fun PlayerResponse.getTotalGoal(): Int {
    return statistics.sumOf {
        it.goals.total
    }
}


fun PlayerResponse.getTotalAssist(): Int {
    return statistics.sumOf {
        it.goals.assists
    }
}


fun PlayerResponse.toStatistic(): List<PlayerStatData> {
    return listOf(
        PlayerStatData(
            StatisticItem.GAME_APPEARANCE.title,
            getTotalAppearance().toString()
        ),

        PlayerStatData(
            StatisticItem.SHOT.title,
            getTotalGoal().toString()
        ),

        PlayerStatData(
            StatisticItem.ASSIST.title,
            getTotalAssist().toString()
        ),
    )
}