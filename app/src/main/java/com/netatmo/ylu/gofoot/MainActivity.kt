package com.netatmo.ylu.gofoot

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.netatmo.ylu.gofoot.livedata.CountriesLiveData
import com.netatmo.ylu.gofoot.ui.LeaguesActivity

class MainActivity : AppCompatActivity() {

    private lateinit var countriesLiveData: CountriesLiveData
    private lateinit var textView: TextView
    private lateinit var buttonLeague: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById(R.id.textview)
        buttonLeague = findViewById(R.id.btn_league)
        buttonLeague.setOnClickListener {
            LeaguesActivity.start(this)
        }
        countriesLiveData = ViewModelProvider(this).get(CountriesLiveData::class.java)
        countriesLiveData.countryLiveData.observe(this,
            { t ->
                t?.firstOrNull()?.name?.let {
                    textView.text = it
                }
            })
    }

}