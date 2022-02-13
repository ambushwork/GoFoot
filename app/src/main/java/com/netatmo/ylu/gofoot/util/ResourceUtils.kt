package com.netatmo.ylu.gofoot.util

import android.content.Context
import android.util.TypedValue
import androidx.annotation.DimenRes
import com.netatmo.ylu.gofoot.R


fun Context.getDimension(@DimenRes dimenRes: Int): Int =
    resources.getDimensionPixelOffset(dimenRes)

fun Context.getSelectableItemBackground(): Int {
    val typedValue = TypedValue()
    theme.resolveAttribute(R.attr.selectableItemBackground, typedValue, true)
    return typedValue.resourceId
}