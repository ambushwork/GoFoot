package com.netatmo.ylu.gofoot.ui.team

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.netatmo.ylu.gofoot.R
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_info)
        val teamId: Int = intent.extras?.getInt(BUNDLE_TEAM_ID) ?: error("Can't get BUNDLE_TEAM_ID")
        viewPager = findViewById(R.id.view_pager)
        viewPager.adapter = TeamPagerAdapter(teamId, this)
        tabLayout = findViewById(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text =
                getString(TeamPage.values().asList().first { it.position == position }.title)
        }.attach()
        headerView = findViewById(R.id.view_team_info_header)
        teamViewModel.getTeamById(teamId)
            .observe(this@TeamInformationActivity, { t -> headerView.setViewModel(t) })

    }
}