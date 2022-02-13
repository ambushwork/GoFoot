package com.netatmo.ylu.gofoot.ui.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.netatmo.ylu.gofoot.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamFixtureFragment : Fragment() {
    companion object {
        const val ARG_INDEX = "index"
        const val ARG_TEAM_ID = "team_id"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_team_page, container, false)
    }
}