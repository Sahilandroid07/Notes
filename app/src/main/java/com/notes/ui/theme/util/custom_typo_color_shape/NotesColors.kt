package com.notes.ui.theme.util.custom_typo_color_shape

import androidx.compose.ui.graphics.Color

class NotesColorScheme(
    val white: Color = NotesColor.white,
    val black: Color = NotesColor.black,
    val off_blue: Color = NotesColor.off_blue,
    val border_medium: Color = NotesColor.border_medium,
    val light_green: Color = NotesColor.light_green,
    val orange: Color = NotesColor.orange,
    val dark_grey: Color = NotesColor.dark_grey,
    val off_white: Color = NotesColor.off_white,
    val blue: Color = NotesColor.blue,
    val purple: Color = NotesColor.purple,
    val bold_green: Color = NotesColor.bold_green,
    val lightest_green: Color = NotesColor.lightest_green,
    val lightest_red: Color = NotesColor.lightest_red,
    val pinkish_purple: Color = NotesColor.pinkish_purple,
    val baby_pink: Color = NotesColor.baby_pink,
    val button_green: Color = NotesColor.button_green
)


object NotesColor {
    val white = Color(0xFFFFFFFF)
    val black = Color(0xFF000000)
    val off_blue = Color(0xFF74DAF3)
    val border_medium = Color(0XFFE2E5DB)
    val light_green = Color(0XFFE3F7BB)
    val orange = Color(0xFFE2BC6F)
    val dark_grey = Color(0xFF3A3939)
    val off_white = Color(0XFFF4F4F4)
    val blue = Color(0XFF2A55B4)
    val purple = Color(0XFF8B97F0)
    val bold_green = Color(0XFFB3DA39)
    val lightest_green = Color(0XFFECFDD2)
    val lightest_red = Color(0xFFF8A9C8)
    val pinkish_purple = Color(0XFFCAA7FC)
    val baby_pink = Color(0XFFF3DCFE)
    val button_green = Color(0XFF81D829)
}

fun lightColorScheme(): NotesColorScheme = NotesColorScheme()