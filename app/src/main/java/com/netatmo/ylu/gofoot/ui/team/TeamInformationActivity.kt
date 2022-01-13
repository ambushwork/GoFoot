package com.netatmo.ylu.gofoot.ui.team

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.netatmo.ylu.gofoot.R
import com.netatmo.ylu.gofoot.repository.TeamInfoViewModel
import com.netatmo.ylu.gofoot.repository.TeamRepository
import com.netatmo.ylu.gofoot.room.GoFootRoomDatabase

class TeamInformationActivity : AppCompatActivity() {

    private lateinit var viewModel: TeamInfoViewModel

    private lateinit var headerView: TeamInformationHeaderView


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

        val repo = TeamRepository(GoFootRoomDatabase.getDatabase(this).teamDao())
        viewModel =
            ViewModelProvider(
                this, TeamInfoViewModel.FACTORY(repo)
            )[TeamInfoViewModel::class.java].apply {
                
                getTeamById(teamId).observe(
                    this@TeamInformationActivity,
                    { t -> headerView.setViewModel(t) })
            }

    }
}