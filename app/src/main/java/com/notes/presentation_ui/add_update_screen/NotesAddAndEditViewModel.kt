package com.notes.presentation_ui.add_update_screen

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.notes.domain.repository.NotesRepository
import com.notes.domain.room.NotesDataEntity
import com.notes.presentation_ui.notes_screen.NotesUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import javax.inject.Inject

@HiltViewModel
class NotesAddAndEditViewModel @Inject constructor(
    private val repository: NotesRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var isUpdated: Boolean = false
    val noteArgs = savedStateHandle.get<String?>("notesData")

    private val _uiState = MutableStateFlow(NotesAddUiState())
    val uiState = _uiState
        .onStart {
            if (!noteArgs.isNullOrBlank() && noteArgs != "null") {
                val parsedData = Json.decodeFromString<NotesUiModel>(noteArgs)
                _uiState.update {
                    it.copy(
                        id = parsedData.id,
                        notesData = parsedData,
                        title = parsedData.title,
                        description = parsedData.description,
                        backgroundColor = parsedData.backgroundColor,
                        textColor = parsedData.textColor
                    )
                }
            } else isUpdated = false
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = _uiState.value
        )

    fun onAction(intent: NoteAddAndEditIntent) {
        when(intent) {
            is NoteAddAndEditIntent.OnTitleValueChangeIntent -> onTitleValueChange(intent.title)
            is NoteAddAndEditIntent.OnDescriptionValueChangeIntent -> onDescriptionValueChange(intent.description)
            is NoteAddAndEditIntent.OnSaveAndUpdateClickIntent -> onSaveAndUpdateClick()
            is NoteAddAndEditIntent.OnBackgroundChangeClickIntent -> onBackgroundChangeClick(intent.color)
        }
    }

    private fun onTitleValueChange(title: String) {
        _uiState.update { it.copy(title = title) }
    }

    private fun onDescriptionValueChange(description: String) {
        _uiState.update { it.copy(description = description) }
    }

    private fun onBackgroundChangeClick(color: Color) {
        val hexColor = "#%08X".format(color.value.toLong())
        _uiState.update { it.copy(backgroundColor = hexColor) }
    }


    private fun onSaveAndUpdateClick() {
        val title = uiState.value.title
        val description = uiState.value.description
        val backgroundColor = uiState.value.backgroundColor
        val textColor = uiState.value.textColor
        viewModelScope.launch {
            val newNote = NotesDataEntity(
                id = uiState.value.id ?: 0,
                title = title ?: "",
                description = description ?: "",
                createdAt = System.currentTimeMillis(),
                updatedAt = if (isUpdated) System.currentTimeMillis() else null,
                backgroundColor = backgroundColor,
                textColor = textColor
            )
            Log.d("NotesDebug---------------------->", "Saving note with id=${newNote.id}")
            repository.addAndUpdateNotes(newNote)
        }
    }

}