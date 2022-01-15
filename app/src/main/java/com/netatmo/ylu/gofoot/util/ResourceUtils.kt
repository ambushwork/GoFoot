package com.netatmo.ylu.gofoot.util

import android.content.Context
import androidx.annotation.DimenRes

fun Context.getDimension(@DimenRes dimenRes: Int): Int =
    resources.getDimensionPixelOffset(dimenRes)