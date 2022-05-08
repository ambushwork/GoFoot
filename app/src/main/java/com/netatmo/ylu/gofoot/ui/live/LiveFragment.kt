package com.netatmo.ylu.gofoot.ui.live

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.netatmo.ylu.gofoot.R
import com.netatmo.ylu.gofoot.ui.fixtures.FixturesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LiveFragment : Fragment() {

    val viewModel: LiveViewModel by activityViewModels()

    companion object {
        fun getInstance(): Fragment {
            return LiveFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.activity_fixtures, container, false)
        val swipeRefreshLayout =
            rootView.findViewById<SwipeRefreshLayout>(R.id.swipe_refresh_layout)
        val filterButton = rootView.findViewById<FloatingActionButton>(R.id.filter_button)
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.updateLiveFixtures()
        }
        viewModel.loading.observe(this) {
            swipeRefreshLayout.isRefreshing = it
        }
        filterButton.setOnClickListener {
            viewModel.toggleFiltered()
        }
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.recycler_view_fixtures)
        rootView.findViewById<ShimmerFrameLayout>(R.id.placeholder_shimmer_container).apply {
            viewModel.shimmerState.observe(this@LiveFragment, { loading ->
                if (loading) {
                    startShimmer()
                } else {
                    stopShimmer()
                }
                isVisible = loading
            })
        }
        val adapter = FixturesAdapter().apply {
            listener = object : FixturesAdapter.Listener {
                override fun onItemClicked(id: Int) {
                    //TeamsActivity.start(this@FixturesActivity, id)
                }
            }
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.resultFlow.observe(this, { t -> adapter.list = t })
        return rootView
    }
}