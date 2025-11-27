package com.notes.ui.theme.util

import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt

fun String.toComposeColor(): Color {
    // Remove the '#' character if present
    val colorCode = this.removePrefix("#")

    // Parse the hexadecimal string to a long integer
    val colorLong = colorCode.toLong(16)

    // Add alpha channel if missing (assume fully opaque)
    val finalColor = when (colorCode.length) {
        6 -> 0xFF000000 or colorLong // Add alpha for 6-digit codes
        8 -> colorLong // Use as-is for 8-digit codes
        else -> throw IllegalArgumentException("Invalid color code: $this")
    }

    // Create and return the Compose Color object
    return Color(finalColor)
}

fun String.toColorCompose(): Color {
    return runCatching { Color(this.toColorInt()) }.getOrElse { Color(0xFF000000) }
}