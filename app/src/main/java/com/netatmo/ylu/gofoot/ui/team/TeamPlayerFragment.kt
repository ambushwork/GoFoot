package com.netatmo.ylu.gofoot.ui.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.netatmo.ylu.gofoot.R
import com.netatmo.ylu.gofoot.repository.PlayerViewModel
import com.netatmo.ylu.gofoot.ui.player.PlayersView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamPlayerFragment : Fragment() {

    companion object {
        const val ARG_INDEX = "index"
        const val ARG_TEAM_ID = "team_id"
    }

    private lateinit var rootLayout: ViewGroup

    private val playerViewModel: PlayerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_team_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rootLayout = view.findViewById(R.id.root_layout)
        arguments?.takeIf { it.containsKey(ARG_INDEX) && it.containsKey(ARG_TEAM_ID) }?.apply {
            TeamPage.fromPosition(getInt(ARG_INDEX)).apply {
                when (this) {
                    TeamPage.FIXTURES -> rootLayout.addView(TeamFixtureView(requireContext()))
                    TeamPage.PLAYERS -> {
                        PlayersView(requireContext()).apply {
                            viewModel = playerViewModel
                            lifecycleOwner = this@TeamPlayerFragment
                            teamId = getInt(ARG_TEAM_ID)
                            rootLayout.addView(this)
                        }

                    }
                }
            }
        }
    }
}