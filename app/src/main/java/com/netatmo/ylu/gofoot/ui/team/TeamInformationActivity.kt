package com.netatmo.ylu.gofoot.ui.team

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.netatmo.ylu.gofoot.R
import com.netatmo.ylu.gofoot.repository.TeamRepository
import com.netatmo.ylu.gofoot.repository.TeamsViewModel
import com.netatmo.ylu.gofoot.repository.getTeam
import com.netatmo.ylu.gofoot.room.TeamRoomDatabase

class TeamInformationActivity : AppCompatActivity() {

    private lateinit var viewModel: TeamsViewModel

    private lateinit
    var headerView: TeamInformationHeaderView


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
        headerView = findViewById(R.id.view_team_info_header)
        val repo = TeamRepository(TeamRoomDatabase.getDatabase(this).teamDao())
        viewModel =
            ViewModelProvider(this, TeamsViewModel.FACTORY(repo))[TeamsViewModel::class.java]
        val teamId: Int = intent.extras?.getInt(BUNDLE_TEAM_ID) ?: error("Can't get BUNDLE_TEAM_ID")
        viewModel.teams.value?.getTeam(teamId)?.let {
            headerView.setViewModel(it)
        }
    }
}