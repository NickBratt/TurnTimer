package com.labbratt.turntimer

import java.util.concurrent.TimeUnit

object TimeFormatter {
    private const val TIME_FORMAT = "%02d:%02d:%02d"

    fun Long.formatTime(): String = String.format(
        TIME_FORMAT,
        TimeUnit.MILLISECONDS.toHours(this),
        TimeUnit.MILLISECONDS.toMinutes(this) % 60,
        TimeUnit.MILLISECONDS.toSeconds(this) % 60,
    )
}