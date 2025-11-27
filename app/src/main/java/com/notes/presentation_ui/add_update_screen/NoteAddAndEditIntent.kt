package com.notes.presentation_ui.add_update_screen

import androidx.compose.ui.graphics.Color

sealed interface NoteAddAndEditIntent {
    data class OnTitleValueChangeIntent(val title: String): NoteAddAndEditIntent
    data class OnDescriptionValueChangeIntent(val description: String): NoteAddAndEditIntent
    data object OnSaveAndUpdateClickIntent: NoteAddAndEditIntent
    data class OnBackgroundChangeClickIntent(val color: Color): NoteAddAndEditIntent
}