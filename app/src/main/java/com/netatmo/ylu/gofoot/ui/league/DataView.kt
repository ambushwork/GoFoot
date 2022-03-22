package com.netatmo.ylu.gofoot.ui.league

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.netatmo.ylu.gofoot.model.League
import com.netatmo.ylu.gofoot.model.TeamInfo
import com.netatmo.ylu.gofoot.repository.LeagueViewModel
import com.netatmo.ylu.gofoot.repository.TeamsViewModel
import com.netatmo.ylu.gofoot.util.getSelectedLeagueIds

@Composable
fun DataView(viewModel: LeagueViewModel) {
    val leagues = getSelectedLeagueIds()
    val state = viewModel.getLeaguesByIds(leagues).observeAsState()
    val tabPosition = remember { mutableStateOf(0) }
    Column {
        TextTabs(
            tabPosition.value,
            state.value ?: emptyList(),
            onClick = { index ->
                tabPosition.value = index
            }
        )
        LeagueTeamsView(leagues[tabPosition.value])
    }
}


@Composable
fun TextTabs(
    tabIndex: Int,
    leagues: List<League>,
    onClick: ((index: Int) -> Unit)
) {
    if (leagues.isNotEmpty()) {
        TabRow(
            selectedTabIndex = tabIndex,
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
        ) {
            leagues.forEachIndexed { index, league ->
                Tab(
                    selected = tabIndex == index,
                    onClick = { onClick(index) },
                    text = {
                        Text(text = league.name)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LeagueTeamsView(
    id: String,
    viewModel: TeamsViewModel = hiltViewModel()
) {
    viewModel.update(id, 2021)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        val teams = viewModel.getTeamsByLeagueId(id).observeAsState(initial = emptyList())
        LazyColumn {
            itemsIndexed(items = teams.value) { index, item ->
                TeamItemView(index, teams.value[index])
            }
        }
    }
}

@Composable
fun TeamItemView(index: Int, team: TeamInfo) {
    Row(modifier = Modifier.padding(16.dp)) {

        Text(
            text = index.toString(),
            modifier = Modifier.weight(1f)
        )
        Image(
            painter = rememberImagePainter(team.team.logo),
            contentDescription = "",
            modifier = Modifier
                .height(24.dp)
                .width(24.dp)
                .weight(1f)
        )
        Text(
            text = team.team.name,
            modifier = Modifier.weight(3f)
        )

    }
}


