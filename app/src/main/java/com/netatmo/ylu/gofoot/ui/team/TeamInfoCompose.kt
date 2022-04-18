package com.netatmo.ylu.gofoot.ui.team

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.netatmo.ylu.gofoot.model.Team
import com.netatmo.ylu.gofoot.model.fixture.FixtureResponse
import com.netatmo.ylu.gofoot.repository.FixturesViewModel
import com.netatmo.ylu.gofoot.repository.TeamInfoViewModel
import com.netatmo.ylu.gofoot.util.strToDayMonth

val headerHeight = 120.dp

@ExperimentalMaterialApi
@Composable
fun TeamInfoView(
    teamId: Int,
    viewModel: TeamInfoViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val scrollState = rememberLazyListState()
    val scrollUpState = viewModel.scrollUp.observeAsState()
    viewModel.updateScrollPosition(scrollState.firstVisibleItemIndex)
    Box(modifier = Modifier.fillMaxSize()) {
        TeamInfoBody(teamId, scrollState)
        TeamInfoHeader(teamId, scrollUpState)
    }


}

@Composable
fun TeamInfoHeader(
    teamId: Int,
    scrollUpState: State<Boolean?>,
    viewModel: TeamInfoViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val teamInfo = viewModel.getTeamById(teamId).observeAsState().value
    val position by animateFloatAsState(
        targetValue = if (scrollUpState.value == true)
            with(LocalDensity.current) {
                -headerHeight.toPx()
            }
        else
            0f
    )
    Surface(modifier = Modifier.graphicsLayer {
        translationY = (position)
    }) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(headerHeight)
                .background(color = MaterialTheme.colors.primary)
        ) {
            teamInfo?.let {
                Image(
                    painter = rememberImagePainter(it.team.logo),
                    contentDescription = "logo",
                    modifier = Modifier.padding(8.dp)
                )
                Column {
                    Text(
                        text = it.team.name,
                        color = MaterialTheme.colors.onPrimary,
                        modifier = Modifier.padding(8.dp),
                        style = MaterialTheme.typography.h6
                    )

                    Text(
                        text = it.venue.name,
                        color = MaterialTheme.colors.onPrimary,
                        modifier = Modifier.padding(8.dp),
                        style = MaterialTheme.typography.subtitle2
                    )
                }

            }
        }
    }

}

@Composable
fun TeamInfoBody(
    teamId: Int,
    scrollState: LazyListState,
    viewModel: FixturesViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    viewModel.getIncomingFixtures(teamId)
    val state = viewModel.liveData.observeAsState(initial = emptyList())
    LazyColumn(
        state = scrollState,
        contentPadding = PaddingValues(top = headerHeight)
    ) {
        items(state.value) { item ->
            FixtureItem(item)
        }
    }
}

@Composable
fun FixtureItem(fixtureResponse: FixtureResponse) {
    Column {
        Box(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = strToDayMonth(fixtureResponse.fixture.date),
                modifier = Modifier.align(Alignment.CenterStart)
            )
            Text(
                text = fixtureResponse.league.name,
                modifier = Modifier.align(Alignment.Center)
            )

        }
        Row {
            TeamLogoColumn(fixtureResponse.fixtureTeams.home, modifier = Modifier.weight(1f))
            TeamLogoColumn(fixtureResponse.fixtureTeams.away, modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun TeamLogoColumn(team: Team, modifier: Modifier) {
    Column(modifier = modifier.padding(8.dp)) {
        Image(
            painter = rememberImagePainter(team.logo),
            contentDescription = "home",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(8.dp)
                .height(36.dp)
                .width(36.dp)
        )
        Text(
            text = team.name,
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}