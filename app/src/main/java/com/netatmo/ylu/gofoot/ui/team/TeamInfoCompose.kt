package com.netatmo.ylu.gofoot.ui.team

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.netatmo.ylu.gofoot.repository.FixturesViewModel
import com.netatmo.ylu.gofoot.repository.TeamInfoViewModel

@ExperimentalMaterialApi
@Composable
fun TeamInfoView(
    teamId: Int,
    viewModel: TeamInfoViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val scrollState = rememberLazyListState()
    val scrollUpState = viewModel.scrollUp.observeAsState()
    viewModel.updateScrollPosition(scrollState.firstVisibleItemIndex)
    BoxWithConstraints {

        Column(Modifier.fillMaxSize()) {
            TeamInfoHeader(scrollUpState)
            TeamInfoBody(teamId, scrollState)
        }

    }


}

@Composable
fun TeamInfoHeader(scrollUpState: State<Boolean?>) {
    val position by animateFloatAsState(targetValue = if (scrollUpState.value == true) -160f else 0f)
    Surface(modifier = Modifier.graphicsLayer {
        translationY = (position)
    }) {
        Box(
            Modifier
                .height(300.dp)
                .background(color = Color.DarkGray)
        ) {
            Text(text = "header", modifier = Modifier.align(Alignment.Center))
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
    LazyColumn(state = scrollState) {
        items(state.value) { item ->
            Text(
                text = "${item.fixtureTeams.home.name}vs${item.fixtureTeams.away.name}",
                modifier = Modifier.padding(32.dp)
            )
        }
    }

}