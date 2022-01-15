package com.netatmo.ylu.gofoot.ui.team

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.netatmo.ylu.gofoot.R
import com.netatmo.ylu.gofoot.repository.PlayerViewModel
import com.netatmo.ylu.gofoot.repository.TeamInfoViewModel
import com.netatmo.ylu.gofoot.ui.player.PlayersView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamInformationActivity : AppCompatActivity() {

    private val teamViewModel: TeamInfoViewModel by viewModels()

    private val playerViewModel: PlayerViewModel by viewModels()

    private lateinit var headerView: TeamInformationHeaderView

    private lateinit var playersView: PlayersView


    companion object {

        private const val BUNDLE_TEAM_ID = "BUNDLE_TEAM_ID"

        fun start(context: Context, teamId: Int) {
            Intent(context, TeamInformationActivity::class.java).apply {
                putExtra(BUNDLE_TEAM_ID, teamId)
                context.startActivity(this)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_info)
        val teamId: Int = intent.extras?.getInt(BUNDLE_TEAM_ID) ?: error("Can't get BUNDLE_TEAM_ID")
        headerView = findViewById(R.id.view_team_info_header)
        playersView = findViewById<PlayersView>(R.id.view_team_player_view).apply {
            this.viewModel = playerViewModel
            this.teamId = teamId
        }
        teamViewModel.getTeamById(teamId)
            .observe(this@TeamInformationActivity, { t -> headerView.setViewModel(t) })

    }
}