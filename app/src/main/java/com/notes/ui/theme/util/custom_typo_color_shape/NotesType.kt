package com.notes.ui.theme.util.custom_typo_color_shape

import androidx.compose.ui.text.TextStyle
import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Immutable
class NotesTypography(
    val text_25_800: TextStyle = TypographyDefault.text_25_800,
    val text_20_800: TextStyle = TypographyDefault.text_20_800,
    val text_25_500: TextStyle = TypographyDefault.text_25_500,
    val text_20_500: TextStyle = TypographyDefault.text_20_500,
    val text_25_300: TextStyle = TypographyDefault.text_25_300,
    val text_20_300: TextStyle = TypographyDefault.text_20_300,
    val text_18_300: TextStyle = TypographyDefault.text_18_300,
    val text_18_500: TextStyle = TypographyDefault.text_18_500,
    val text_18_600: TextStyle = TypographyDefault.text_18_600,
    val text_18_800: TextStyle = TypographyDefault.text_18_800,
    val text_16_300: TextStyle = TypographyDefault.text_16_300,
    val text_16_500: TextStyle = TypographyDefault.text_16_500,
    val text_16_600: TextStyle = TypographyDefault.text_16_600,
    val text_16_800: TextStyle = TypographyDefault.text_16_800,
    val text_14_300: TextStyle = TypographyDefault.text_14_300,
    val text_14_500: TextStyle = TypographyDefault.text_14_500,
    val text_14_600: TextStyle = TypographyDefault.text_14_600,
    val text_14_800: TextStyle = TypographyDefault.text_14_800
)

private object TypographyDefault {
    val text_25_800: TextStyle = TextStyle(
        fontWeight = FontWeight(800),
        fontSize = 25.sp,
    )

    val text_20_800: TextStyle = TextStyle(
        fontWeight = FontWeight(800),
        fontSize = 20.sp,
    )

    val text_25_500: TextStyle = TextStyle(
        fontWeight = FontWeight(500),
        fontSize = 25.sp,
    )

    val text_20_500: TextStyle = TextStyle(
        fontWeight = FontWeight(500),
        fontSize = 20.sp,
    )

    val text_25_300: TextStyle = TextStyle(
        fontWeight = FontWeight(300),
        fontSize = 25.sp,
    )

    val text_20_300: TextStyle = TextStyle(
        fontWeight = FontWeight(300),
        fontSize = 20.sp,
    )

    val text_18_300: TextStyle = TextStyle(
        fontWeight = FontWeight(300),
        fontSize = 18.sp,
    )

    val text_18_500: TextStyle = TextStyle(
        fontWeight = FontWeight(500),
        fontSize = 18.sp,
    )
    val text_18_600: TextStyle = TextStyle(
        fontWeight = FontWeight(600),
        fontSize = 18.sp,
    )

    val text_18_800: TextStyle = TextStyle(
        fontWeight = FontWeight(800),
        fontSize = 18.sp,
    )

    val text_16_300: TextStyle = TextStyle(
        fontWeight = FontWeight(300),
        fontSize = 16.sp,
    )

    val text_16_500: TextStyle = TextStyle(
        fontWeight = FontWeight(500),
        fontSize = 16.sp,
    )
    val text_16_600: TextStyle = TextStyle(
        fontWeight = FontWeight(600),
        fontSize = 16.sp,
    )

    val text_16_800: TextStyle = TextStyle(
        fontWeight = FontWeight(800),
        fontSize = 16.sp,
    )

    val text_14_300: TextStyle = TextStyle(
        fontWeight = FontWeight(300),
        fontSize = 14.sp,
    )

    val text_14_500: TextStyle = TextStyle(
        fontWeight = FontWeight(500),
        fontSize = 14.sp,
    )
    val text_14_600: TextStyle = TextStyle(
        fontWeight = FontWeight(600),
        fontSize = 14.sp,
    )

    val text_14_800: TextStyle = TextStyle(
        fontWeight = FontWeight(800),
        fontSize = 14.sp,
    )


}