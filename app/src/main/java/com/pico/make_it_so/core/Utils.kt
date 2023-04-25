package com.pico.make_it_so.core

import java.text.SimpleDateFormat
import java.util.*

fun getDate(timeMillis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.US)
    val selectedDate = Calendar.getInstance()
    selectedDate.timeInMillis = timeMillis
    val todayDate = Calendar.getInstance()
    val tomorrowDate = Calendar.getInstance()
    tomorrowDate.add(Calendar.DATE, 1)
    return when (formatter.format(selectedDate.timeInMillis)) {
        formatter.format(todayDate.timeInMillis) -> {
            "Today"
        }
        formatter.format(tomorrowDate.timeInMillis) -> {
            "Tomorrow"
        }
        else -> {
            formatter.format(selectedDate.timeInMillis)
        }
    }
}