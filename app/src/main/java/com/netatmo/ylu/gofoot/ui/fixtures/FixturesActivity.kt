package com.netatmo.ylu.gofoot.ui.fixtures

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.netatmo.ylu.gofoot.R
import com.netatmo.ylu.gofoot.repository.FixturesViewModel

class FixturesActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, FixturesActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fixtures)
        val fixtureLiveData = ViewModelProvider(this).get(FixturesViewModel::class.java)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_fixtures)
        val adapter = FixturesAdapter().apply {
            listener = object : FixturesAdapter.Listener {
                override fun onItemClicked(id: Int) {
                    //TeamsActivity.start(this@FixturesActivity, id)
                }
            }
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        fixtureLiveData.liveData.observe(this, { t -> adapter.list = t })
        fixtureLiveData.liveUpdate()
    }
}