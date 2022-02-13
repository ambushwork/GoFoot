package com.netatmo.ylu.gofoot.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.netatmo.ylu.gofoot.R
import com.netatmo.ylu.gofoot.model.PlayerResponse
import com.netatmo.ylu.gofoot.repository.PlayerStatisticViewModel
import com.netatmo.ylu.gofoot.ui.player.PlayerStatAdapter
import com.netatmo.ylu.gofoot.util.toStatistic
import com.squareup.picasso.Picasso

class PlayerBottomSheet : BottomSheetDialogFragment() {

    companion object {
        const val PLAYER_ID = "PLAYER_ID"
        const val TAG = "PlayerBottomSheet"
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: PlayerStatisticViewModel
    private lateinit var ivPlayer: ImageView
    private lateinit var tvName: TextView
    private lateinit var tvTeam: TextView
    private lateinit var tvAge: TextView
    private lateinit var tvPosition: TextView

    private var playerResponse: PlayerResponse? = null
        set(value) {
            field = value
            value?.let {
                updateView(it)
            }
        }

    override fun getTheme(): Int = R.style.AppBottomSheetDialogTheme

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.player_bottom_sheet_view, container, false)
        val data = arguments?.getInt(PLAYER_ID) ?: error("PLAYER_ID not set for the fragment")

        tvName = rootView.findViewById(R.id.tv_player_name)
        ivPlayer = rootView.findViewById(R.id.iv_player_icon)
        tvAge = rootView.findViewById(R.id.tv_player_age)
        recyclerView =
            rootView.findViewById<RecyclerView>(R.id.view_statistic_recycler_view).apply {
                layoutManager = GridLayoutManager(requireContext(), 4)
            }
        viewModel = ViewModelProvider(this)[PlayerStatisticViewModel::class.java]
        viewModel.liveData.observe(this, { playerResponse ->
            playerResponse?.let {
                this@PlayerBottomSheet.playerResponse = it
            }
        })
        viewModel.getPlayer(data)
        return rootView
    }

    private fun updateView(playerResponse: PlayerResponse) {
        recyclerView.adapter = PlayerStatAdapter(playerResponse.toStatistic())
        tvName.text = playerResponse.player.name
        Picasso.get().load(playerResponse.player.photo).into(ivPlayer)
        tvAge.text = playerResponse.player.age.toString()
    }

}

