package com.netatmo.ylu.gofoot.ui.league

import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun DataView() {
    TextTabs()
}


@Composable
fun TextTabs() {
    var tabIndex = remember { mutableStateOf(0) }
    val tabData = listOf(
        "Premier League",
        "La liga",
        "La Seria A"
    )

    TabRow(selectedTabIndex = tabIndex.value) {
        tabData.forEachIndexed { index, text ->
            Tab(
                selected = tabIndex.value == index,
                onClick = {
                    tabIndex.value = index
                },
                text = {
                    Text(text = text)
                }
            )
        }
    }
}


