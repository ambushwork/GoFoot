package com.netatmo.ylu.gofoot.ui.live

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import com.netatmo.ylu.gofoot.R
import com.netatmo.ylu.gofoot.repository.FixturesViewModel
import com.netatmo.ylu.gofoot.ui.fixtures.FixturesAdapter

class LiveFragment : Fragment() {

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
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.recycler_view_fixtures)
        val viewModel = ViewModelProvider(this)[FixturesViewModel::class.java]
        rootView.findViewById<ShimmerFrameLayout>(R.id.placeholder_shimmer_container).apply {
            viewModel.loading.observe(this@LiveFragment, { loading ->
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
        viewModel.liveData.observe(this, { t -> adapter.list = t })
        viewModel.liveUpdate()
        return rootView
    }
}