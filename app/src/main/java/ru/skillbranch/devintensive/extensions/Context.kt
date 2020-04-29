package ru.skillbranch.devintensive.extensions

import android.content.Context
import kotlin.math.roundToInt

fun Context.convertDpToPx(dp: Int): Float {
    return (dp * this.resources.displayMetrics.density)
}

fun Context.convertPxToDp(px: Float): Int {
    return (px / this.resources.displayMetrics.density).roundToInt()
}

fun Context.convertSpToPx(sp: Int): Int {
    return sp * this.resources.displayMetrics.scaledDensity.roundToInt()
}