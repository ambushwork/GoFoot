package com.netatmo.ylu.gofoot.ui.team

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.netatmo.ylu.gofoot.repository.TeamInfoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamInformationActivity : AppCompatActivity() {

    private val teamViewModel: TeamInfoViewModel by viewModels()

    private lateinit var headerView: TeamInformationHeaderView

    private lateinit var tabLayout: TabLayout

    private lateinit var viewPager: ViewPager2


    companion object {

        private const val BUNDLE_TEAM_ID = "BUNDLE_TEAM_ID"

        fun start(context: Context, teamId: Int) {
            Intent(context, TeamInformationActivity::class.java).apply {
                putExtra(BUNDLE_TEAM_ID, teamId)
                context.startActivity(this)
            }
        }
    }

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val teamId: Int = intent.extras?.getInt(BUNDLE_TEAM_ID) ?: error("Can't get BUNDLE_TEAM_ID")
        setContent {
            TeamInfoView(teamId)
        }
    }
}