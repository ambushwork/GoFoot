package com.netatmo.ylu.gofoot.ui.team

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.netatmo.ylu.gofoot.R
import com.netatmo.ylu.gofoot.repository.FixturesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamFixtureView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    private val adapter: TeamFixtureAdapter

    var viewModel: FixturesViewModel? = null

    var lifecycleOwner: LifecycleOwner? = null

    var teamId: Int? = null
        set(value) {
            value?.let { teamId ->
                lifecycleOwner?.let {
                    viewModel?.liveData?.observe(it, { t -> adapter.fixtures = t })
                }
                viewModel?.getIncomingFixtures(teamId)
            }

            field = value
        }

    init {
        inflate(context, R.layout.view_team_fixture, this)
        findViewById<RecyclerView>(R.id.recycler_view).apply {
            this.adapter = TeamFixtureAdapter().apply {
                layoutManager = LinearLayoutManager(context)
                this@TeamFixtureView.adapter = this
            }
        }
    }
}