package com.netatmo.ylu.gofoot.ui.profile

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import com.netatmo.ylu.gofoot.ui.league.FavoriteLeaguesActivity

@Composable
fun SettingsList(context: Context) {
    LazyColumn {
        item {
            SettingItem(
                text = "Favorite",
                imageVector = Icons.Filled.Settings,
                onClick = {
                    FavoriteLeaguesActivity.start(context)
                }
            )
            Divider()
        }
    }
}

@Composable
fun SettingItem(
    text: String,
    imageVector: ImageVector,
    onClick: () -> Unit,
) {
    Row(modifier = Modifier.clickable { onClick() }) {
        Icon(
            painter = rememberVectorPainter(image = imageVector),
            contentDescription = "setting_item",
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = text,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .offset(x = 8.dp)
        )
    }
}