package com.notes.converter

import androidx.compose.ui.graphics.Color
import androidx.room.TypeConverter

class BackgroundColorTypeConverter {

    @TypeConverter
    fun fromColorToString(color: Color?): String? {
        return color?.let { "#%08X".format(it.value.toLong()) }
    }

    @TypeConverter
    fun fromStringToColor(value: String?): Color? {
        return value?.let {
            try {
                val colorLong = it.removePrefix("#").toULong(16)
                Color(colorLong)
            } catch (e: Exception) {
                null
            }
        }
    }
}


