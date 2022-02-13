package com.netatmo.ylu.gofoot.ui.player

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.StringRes
import com.netatmo.ylu.gofoot.R

class StatisticItemView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val tvTitle: TextView
    private val tvValue: TextView

    init {
        inflate(context, R.layout.item_statistic_view, this)
        tvTitle = findViewById(R.id.tv_title)
        tvValue = findViewById(R.id.tv_value)
    }

    fun setViewModel(@StringRes title: Int, value: String) {
        tvTitle.setText(title)
        tvValue.text = value
    }
}