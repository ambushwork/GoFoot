package com.netatmo.ylu.gofoot.ui.league

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.netatmo.ylu.gofoot.model.League
import com.netatmo.ylu.gofoot.repository.LeagueViewModel

@Composable
fun FavoriteLeaguesView(viewModel: LeagueViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {

    val state = viewModel.getLeagues().observeAsState()
    state.value?.let {
        LazyColumn {
            items(it.size) { index ->
                FavoriteLeagueItem(league = it[index])
                Divider()
            }

        }
    }

}

@Composable
fun FavoriteLeagueItem(league: League, isSelected: Boolean = false) {
    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(checked = isSelected, onCheckedChange = {

        })
        Spacer(modifier = Modifier.padding(8.dp))
        Image(
            modifier = Modifier.size(24.dp),
            painter = rememberImagePainter(league.logo),
            contentDescription = null
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Text(text = league.name)

    }
}