package com.netatmo.ylu.gofoot.ui.player

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.view.ViewCompat
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.netatmo.ylu.gofoot.R
import com.netatmo.ylu.gofoot.repository.PlayerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlayersView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {
    private val recyclerView: RecyclerView
    private val adapter: PlayersAdapter

    var listener: Listener? = null
    var viewModel: PlayerViewModel? = null

    var lifecycleOwner: LifecycleOwner? = null

    var teamId: Int? = null
        set(value) {
            value?.let { teamId ->
                lifecycleOwner?.let {
                    viewModel?.getPlayerByTeamId(teamId)?.observe(it) { players ->
                        adapter.submitData(it.lifecycle, players)
                    }
                }
            }
            field = value
        }

    init {
        inflate(context, R.layout.view_player_list, this)
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = PlayersAdapter().apply {
            this@PlayersView.adapter = this
            listener = object : PlayersAdapter.Listener {
                override fun onPlayerDetailRequested(playerId: Int) {
                    this@PlayersView.listener?.onPlayerDetailRequested(playerId)
                }

            }
        }
        ViewCompat.setNestedScrollingEnabled(recyclerView, false)
    }

    interface Listener {

        fun onPlayerDetailRequested(playerId: Int)
    }
}