package com.netatmo.ylu.gofoot.util

import com.netatmo.ylu.gofoot.model.fixture.Fixture
import java.text.SimpleDateFormat
import java.util.*

fun getCurrentSeason(): String {
    //todo
    return "2021"
}

/**
 * Get year string like "2022"
 */
fun getCurrentYear(): String {
    Calendar.getInstance().apply {
        timeInMillis = System.currentTimeMillis()
        return get(Calendar.YEAR).toString()
    }
}

/**
 * YYYY-MM-DD
 */
fun getCurrentDate(): String {
    Calendar.getInstance().apply {
        timeInMillis = System.currentTimeMillis()
        return "${get(Calendar.YEAR)}-${normalizeDateFormat(get(Calendar.MONTH) + 1)}-${
            normalizeDateFormat(get(Calendar.DAY_OF_MONTH))
        }"
    }
}

/**
 * YYYY-MM-DD
 */
fun getFutureDate(month: Int): String {
    Calendar.getInstance().apply {
        timeInMillis = System.currentTimeMillis()
        val futureYear = (get(Calendar.MONTH) + month) / 12 + get(Calendar.YEAR)
        val futureMonth = (get(Calendar.MONTH) + month) % 12
        return "$futureYear-${normalizeDateFormat(futureMonth)}-${normalizeDateFormat(get(Calendar.DAY_OF_MONTH))}"
    }
}

fun normalizeDateFormat(monthOrDay: Int): String {
    return if (monthOrDay < 10) {
        "0$monthOrDay"
    } else {
        monthOrDay.toString()
    }
}

fun strToDate(str: String): Date {
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    return format.parse(str) ?: error("date parsing error")
}

fun Fixture.isSameDayWith(otherFixture: Fixture): Boolean {
    val lastMatchDate = strToDate(otherFixture.date)
    val thisMatchDate = strToDate(this.date)
    val lastDay = Calendar.getInstance().let {
        it.time = lastMatchDate
        it.get(Calendar.DAY_OF_YEAR)
    }
    val thisDay = Calendar.getInstance().let {
        it.time = thisMatchDate
        it.get(Calendar.DAY_OF_YEAR)
    }
    return thisDay == lastDay
}