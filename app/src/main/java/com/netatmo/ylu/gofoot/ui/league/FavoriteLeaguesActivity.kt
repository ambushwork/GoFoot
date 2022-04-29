package com.netatmo.ylu.gofoot.ui.league

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.netatmo.ylu.gofoot.repository.LeagueViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteLeaguesActivity : AppCompatActivity() {

    private val viewModel: LeagueViewModel by viewModels()

    companion object {
        @JvmStatic
        fun start(context: Context) {
            Intent(context, FavoriteLeaguesActivity::class.java).apply {
                context.startActivity(this)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.update()
        setContent {
            FavoriteLeaguesView()
        }
    }
}