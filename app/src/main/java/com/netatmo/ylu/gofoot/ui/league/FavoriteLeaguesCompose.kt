package com.netatmo.ylu.gofoot.ui.league

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
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

    val favState = viewModel.favLeagues.observeAsState()
    val unFavState = viewModel.unFavLeagues.observeAsState()

    Column {


        LazyColumn {
            item {
                Title(text = "Favorite")
            }
            favState.value?.let {
                items(it.size) { index ->
                    FavoriteLeagueItem(league = it[index], it[index].favorite) { id, fav ->
                        viewModel.setLocalFavLeague(id, fav)
                    }
                    Divider()
                }
            }

            item {
                Title(text = "Other")
            }

            unFavState.value?.let {
                items(it.size) { index ->
                    FavoriteLeagueItem(league = it[index], it[index].favorite) { id, fav ->
                        viewModel.setLocalFavLeague(id, fav)
                    }
                    Divider()
                }
            }
        }

    }
}

@Composable
private fun Title(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(16.dp),
        style = MaterialTheme.typography.h6
    )
}

@Composable
fun FavoriteLeagueItem(
    league: League,
    fav: Boolean,
    onChecked: (String, Boolean) -> Unit
) {
    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = if (fav) Icons.Filled.Remove else Icons.Filled.Add,
            contentDescription = null,
            modifier = Modifier.clickable {
                onChecked(league.id, !fav)
            }
        )
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