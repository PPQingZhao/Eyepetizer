package com.pp.library_ui.utils

object TimeUtils {

    fun format(sec: Int): String {
        val minute = sec / 60
        val second = sec % 60
        return String.format("%02d:%02d", minute, second)
    }

}