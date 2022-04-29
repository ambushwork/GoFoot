package com.netatmo.ylu.gofoot

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.netatmo.ylu.gofoot.ui.league.LeaguesFragment
import com.netatmo.ylu.gofoot.ui.live.LiveFragment
import com.netatmo.ylu.gofoot.ui.profile.ProfileFragment
import com.netatmo.ylu.gofoot.util.loadFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.page_live -> {
                    loadFragment(R.id.fragment_container, LiveFragment.getInstance())
                    true
                }
                R.id.page_data -> {
                    loadFragment(R.id.fragment_container, LeaguesFragment.getInstance())
                    true
                }
                R.id.page_profile -> {
                    loadFragment(R.id.fragment_container, ProfileFragment.getInstance())
                    true
                }
                else -> false
            }
        }
        loadFragment(R.id.fragment_container, LiveFragment.getInstance())
    }

}