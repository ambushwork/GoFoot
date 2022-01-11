package com.netatmo.ylu.gofoot.ui.team

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.netatmo.ylu.gofoot.R
import com.netatmo.ylu.gofoot.repository.TeamRepository
import com.netatmo.ylu.gofoot.repository.TeamsViewModel
import com.netatmo.ylu.gofoot.room.GoFootRoomDatabase

class TeamsActivity : AppCompatActivity() {

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
        val repo = TeamRepository(GoFootRoomDatabase.getDatabase(this).teamDao())
        val viewModel =
            ViewModelProvider(this, TeamsViewModel.FACTORY(repo))[TeamsViewModel::class.java]
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_teams)
        val adapter = TeamsAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        viewModel.getTeamsByLeagueId(leagueId).observe(this, { t -> adapter.list = t })
        viewModel.update(leagueId, SEASON)
    }
}