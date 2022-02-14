package com.netatmo.ylu.gofoot.ui.league

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.netatmo.ylu.gofoot.R
import com.netatmo.ylu.gofoot.repository.LeagueViewModel
import com.netatmo.ylu.gofoot.ui.team.TeamsActivity
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
    ): View? {
        val rootView = inflater.inflate(R.layout.activity_leagues, container, false)
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.recycler_view_leagues)
        val adapter = LeaguesAdapter().apply {
            listener = object : LeaguesAdapter.Listener {
                override fun onItemClicked(id: String) {
                    TeamsActivity.start(requireContext(), id)
                }
            }
        }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getLeagues().observe(this, { t ->
            adapter.list = t
        })
        viewModel.update()
        return rootView
    }
}