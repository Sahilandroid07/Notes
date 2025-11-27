package com.notes.presentation_ui.notes_screen



sealed interface NotesIntent {
    data object AddButtonClickIntent: NotesIntent
    data class OnDeleteClickIntent(val notesModel: NotesUiModel): NotesIntent
    data class OnNotesClickIntent(val notesModel: NotesUiModel): NotesIntent
}