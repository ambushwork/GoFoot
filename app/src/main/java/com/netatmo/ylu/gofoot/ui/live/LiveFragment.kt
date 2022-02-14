package com.netatmo.ylu.gofoot.ui.live

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        val fixtureLiveData = ViewModelProvider(this)[FixturesViewModel::class.java]
        val adapter = FixturesAdapter().apply {
            listener = object : FixturesAdapter.Listener {
                override fun onItemClicked(id: Int) {
                    //TeamsActivity.start(this@FixturesActivity, id)
                }
            }
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        fixtureLiveData.liveData.observe(this, { t -> adapter.list = t })
        fixtureLiveData.liveUpdate()
        return rootView
    }
}