package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd:MM:yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time

    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val diff = date.time - this.time
    return when (diff) {
        in 0 until 1 * SECOND -> "только что"
        in 1 * SECOND until 45 * SECOND -> "несколько секунд назад"
        in 45 * SECOND until 75 * SECOND -> "минуту назад"
        in 75 * SECOND until 300 * SECOND -> "${(diff / MINUTE)} минуты назад"
        in 300 * SECOND until 45 * MINUTE -> "${(diff / MINUTE)} минут назад"
        in 45 * MINUTE until 75 * MINUTE -> "час назад"
        in 75 * MINUTE until 300 * MINUTE -> "${(diff / HOUR)} часа назад"
        in 300 * MINUTE until 22 * HOUR -> "${(diff / HOUR)} часов назад"
        in 22 * HOUR until 26 * HOUR -> "день назад"
        in 26 * HOUR until 100 * HOUR -> "${(diff / DAY)} дня назад"
        in 100 * HOUR until 360 * DAY -> "${(diff / DAY)} дней назад"
        in 360 * DAY until Long.MAX_VALUE -> "более года назад"

        in -(1 * SECOND) until 0 -> "сейчас"
        in -(45 * SECOND) until -(1 * SECOND) -> "через несколько секунд"
        in -(75 * SECOND) until -(45 * SECOND) -> "через минуту"
        in -(300 * SECOND) until -(75 * SECOND) -> "через ${(diff / MINUTE) * (-1)} минуты"
        in -(45 * MINUTE) until -(300 * SECOND) -> "через ${(diff / MINUTE) * (-1)} минут"
        in -(75 * MINUTE) until -(45 * MINUTE) -> "через час"
        in -(300 * MINUTE) until -(75 * MINUTE) -> "через ${(diff / HOUR) * (-1)} часа"
        in -(22 * HOUR) until -(300 * MINUTE) -> "через ${(diff / HOUR) * (-1)} часов"
        in -(26 * HOUR) until -(22 * HOUR) -> "через день"
        in -(100 * HOUR) until -(26 * HOUR) -> "через ${(diff / DAY) * (-1)} дня"
        in -(360 * DAY) until -(100 * HOUR) -> "через ${(diff / DAY) * (-1)} дней"
        in -(Long.MAX_VALUE) until -(360 * DAY) -> "более чем через год"
        else -> throw IllegalStateException("invalid data")
    }
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}