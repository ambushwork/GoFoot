package com.netatmo.ylu.gofoot.ui.team

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class TeamPagerAdapter(private val teamId: Int, fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int = TeamPage.values().size

    override fun createFragment(position: Int): Fragment {
        val fragment = TeamPlayerFragment()
        fragment.arguments = Bundle().apply {
            putInt(TeamPlayerFragment.ARG_INDEX, position)
            putInt(TeamPlayerFragment.ARG_TEAM_ID, teamId)
        }
        return fragment
    }
}