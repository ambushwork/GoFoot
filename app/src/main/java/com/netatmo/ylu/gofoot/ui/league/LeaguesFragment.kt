package com.netatmo.ylu.gofoot.ui.league

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.netatmo.ylu.gofoot.repository.LeagueViewModel
import com.netatmo.ylu.gofoot.ui.team.TeamInformationActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LeaguesFragment : Fragment() {

    companion object {
        fun getInstance(): Fragment {
            return LeaguesFragment()
        }
    }

    private val viewModel: LeagueViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.update()
        return ComposeView(requireContext()).apply {
            setContent {
                DataView(viewModel, onItemClick = { teamId ->
                    TeamInformationActivity.start(context, teamId)
                })
            }
        }
    }
}
