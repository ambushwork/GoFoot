package com.netatmo.ylu.gofoot.model

import com.google.gson.annotations.SerializedName

data class FixtureResponse(
    @SerializedName("fixture") val fixtures: Fixture,
    @SerializedName("league") val league: League,
    @SerializedName("teams") val fixtureTeams: FixtureTeams,
    @SerializedName("goals") val goals: FixtureGoals
)