package com.netatmo.ylu.gofoot.ui.league

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.netatmo.ylu.gofoot.R
import com.netatmo.ylu.gofoot.livedata.LeagueLiveData
import com.netatmo.ylu.gofoot.ui.team.TeamsActivity

class LeaguesActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, LeaguesActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leagues)
        val leagueLiveData = ViewModelProvider(this).get(LeagueLiveData::class.java)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_leagues)
        val adapter = LeaguesAdapter().apply {
            listener = object : LeaguesAdapter.Listener {
                override fun onItemClicked(id: String) {
                    TeamsActivity.start(this@LeaguesActivity, id)
                }
            }
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        leagueLiveData.liveData.observe(this, { t -> adapter.list = t })
    }
}