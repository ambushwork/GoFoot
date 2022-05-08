package com.netatmo.ylu.gofoot.ui.team

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.netatmo.ylu.gofoot.R
import com.netatmo.ylu.gofoot.repository.TeamsViewModel
import javax.inject.Inject

class TeamsActivity : AppCompatActivity() {


    private lateinit var progressBar: ProgressBar

    @Inject
    lateinit var viewModel: TeamsViewModel

    companion object {
        private const val SEASON = 2021
        private const val BUNDLE_LEAGUE_ID = "BUNDLE_LEAGUE_ID"
        fun start(context: Context, leagueId: String) {
            val intent = Intent(context, TeamsActivity::class.java).apply {
                putExtra(BUNDLE_LEAGUE_ID, leagueId)
            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teams)
        val leagueId = intent.extras?.getString(BUNDLE_LEAGUE_ID) ?: error("Null league id!")
        progressBar = findViewById(R.id.progress_bar)
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_teams)
        val adapter = TeamsAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        viewModel.spinner.observe(this@TeamsActivity, { t -> progressBar.isVisible = t })
        viewModel.getTeamsByLeagueId(leagueId).observe(this, { t -> adapter.list = t })
        viewModel.update(leagueId, SEASON)
    }
}