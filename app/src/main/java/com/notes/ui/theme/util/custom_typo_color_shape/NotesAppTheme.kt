package com.notes.ui.theme.util.custom_typo_color_shape

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

object NotesAppTheme {
    val colorScheme: NotesColorScheme
    @Composable
    @ReadOnlyComposable
    get() = LocalColorScheme.current

    val typography: NotesTypography
    @Composable
    @ReadOnlyComposable
    get() = LocalTypography.current

    val shapes: NotesShapes
    @Composable
    @ReadOnlyComposable
    get() = LocalShapes.current
}

internal val LocalColorScheme = staticCompositionLocalOf { lightColorScheme() }
internal val LocalTypography = staticCompositionLocalOf { NotesTypography() }
internal val LocalShapes = staticCompositionLocalOf { NotesShapes() }