package com.netatmo.ylu.gofoot.ui.player

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.netatmo.ylu.gofoot.R
import com.netatmo.ylu.gofoot.repository.PlayerRepository
import com.netatmo.ylu.gofoot.repository.PlayerViewModel
import com.netatmo.ylu.gofoot.room.GoFootRoomDatabase

class PlayersView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {
    private val recyclerView: RecyclerView
    private val viewModel: PlayerViewModel
    private val adapter: PlayersAdapter

    var teamId: Int? = null
        set(value) {
            value?.let {
                viewModel.getPlayerByTeamId(it).observe(context as AppCompatActivity) { players ->
                    adapter.list = players
                }

                viewModel.update(value)
            }
            field = value
        }

    init {
        inflate(context, R.layout.view_player_list, this)
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val repo = PlayerRepository(GoFootRoomDatabase.getDatabase(context).playerDao())
        viewModel =
            ViewModelProvider(
                context as AppCompatActivity,
                PlayerViewModel.FACTORY(repo)
            )[PlayerViewModel::class.java]

        recyclerView.adapter = PlayersAdapter().apply {
            this@PlayersView.adapter = this
        }

    }
}