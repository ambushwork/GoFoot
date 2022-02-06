package com.netatmo.ylu.gofoot.model.fixture

import com.google.gson.annotations.SerializedName
import com.netatmo.ylu.gofoot.model.League

data class FixtureResponse(
    @SerializedName("fixture") val fixture: Fixture,
    @SerializedName("league") val league: League,
    @SerializedName("teams") val fixtureTeams: FixtureTeams,
    @SerializedName("goals") val goals: FixtureGoals
)