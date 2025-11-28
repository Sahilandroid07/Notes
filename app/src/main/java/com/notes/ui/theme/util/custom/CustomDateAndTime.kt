package com.notes.ui.theme.util.custom

import java.text.SimpleDateFormat
import java.util.Locale

fun simpleDateDDMMMYYYY(timeStamp: Long): String {
    val simpleDate = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    return simpleDate.runCatching { format(timeStamp) }.getOrElse { "" }
}

fun getRealTimeDate(timeStamp: Long): String {
    val currentTime = System.currentTimeMillis()
    val diffTime = currentTime - timeStamp

    return when {
        diffTime < 60_000 -> "Just now"
        diffTime < 3600_000 -> {
            val minutes = diffTime / 60_000
            "$minutes minute${if(minutes == 1L) "" else "s"} ago"
        }
        diffTime < 86400_000 -> {
            val hours = diffTime / 3600_000
            "${hours} hour${if (hours == 1L) "" else "s"} ago"
        }
        diffTime < 604800_000 -> {
            val days = diffTime / 86400_000
            "${days} day${if (days == 1L) "" else "s"} ago"
        }

        else -> simpleDateDDMMMYYYY(timeStamp)
    }
}