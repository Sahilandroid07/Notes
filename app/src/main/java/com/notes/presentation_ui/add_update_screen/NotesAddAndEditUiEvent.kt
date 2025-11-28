package com.notes.presentation_ui.add_update_screen

sealed interface NotesAddAndEditUiEvent {
    data object OnBackClickUiEvent: NotesAddAndEditUiEvent
}