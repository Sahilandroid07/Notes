package com.notes.presentation_ui.add_update_screen

import com.notes.presentation_ui.notes_screen.NotesUiModel

data class NotesAddUiState(
    val id: Int? = null,
    val title: String? = null,
    val description: String? = null,
    val notesData: NotesUiModel? = null,
    val backgroundColor: String? =  null,
    val textColor: String? =  null,
)
