package com.netatmo.ylu.gofoot.ui.team

import android.util.Log
import android.util.SparseArray
import com.netatmo.ylu.gofoot.model.fixture.Fixture
import com.netatmo.ylu.gofoot.model.fixture.FixtureResponse
import com.netatmo.ylu.gofoot.util.isSameDayWith
import com.netatmo.ylu.gofoot.util.strToDate
import java.util.*

class TeamFixtureFeed {

    private val mappedFixture: SparseArray<FixtureResponse> by lazy { SparseArray() }
    private val mappedDate: SparseArray<Date> by lazy { SparseArray() }

    var fixtures: List<FixtureResponse> = emptyList()
        set(value) {
            field = value
            create()
        }

    private fun create() {
        mappedFixture.clear()
        mappedDate.clear()
        var lastFixture: Fixture? = null
        fixtures.sortedBy {
            strToDate(it.fixture.date)
        }.forEach { resp ->
            lastFixture?.takeIf {
                mappedFixture.size() > 0 && resp.fixture.isSameDayWith(it).not()
            }.apply {
                mappedDate.put(getSize(), strToDate(resp.fixture.date))
                lastFixture = resp.fixture
            }
            mappedFixture.put(getSize(), resp)
        }
        Log.d("feed", "finish")
    }

    fun getDate(position: Int): Date? {
        return mappedDate[position]
    }

    fun isDate(position: Int): Boolean {
        return mappedDate[position] != null
    }

    fun getFixture(position: Int): FixtureResponse? {
        return mappedFixture[position]
    }

    fun isFixture(position: Int): Boolean {
        return mappedFixture[position] != null
    }

    fun getSize(): Int {
        return mappedDate.size() + mappedFixture.size()
    }

}