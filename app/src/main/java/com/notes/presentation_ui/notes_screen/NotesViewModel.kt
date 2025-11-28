package com.notes.presentation_ui.notes_screen


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.notes.domain.repository.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val repository: NotesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(NotesUiState())
    val uiState = _uiState
        .onStart { fetchAllNotes() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = _uiState.value
        )

//    val flowState = repository.getAllNotes().stateIn(
//        scope = viewModelScope,
//        started = SharingStarted.WhileSubscribed(5000),
//        initialValue = emptyList()
//    )

    fun onAction(action: NotesIntent) {
        when(action) {
            is NotesIntent.AddButtonClickIntent -> onAddButtonClick()
            is NotesIntent.OnDeleteClickIntent -> onDeleteNoteClick(action.notesModel)
            is NotesIntent.OnNotesClickIntent -> onNoteClick(action.notesModel)
        }
    }


    private fun fetchAllNotes() {
        viewModelScope.launch {
            repository.getAllNotes().collectLatest { notesList ->
                val mappedUiNotesList = notesList.map { it.toNotesUiModel() }
                _uiState.update { it.copy(notesUiList = mappedUiNotesList) }
            }
        }
    }

    private fun onAddButtonClick() {
//        navController.navigate(NotesAddAndEditNotesRoutes.)
    }

    private fun onDeleteNoteClick(noteUiModel: NotesUiModel) {
        val mappedData = noteUiModel.toNoteData()
        viewModelScope.launch {
            repository.deleteNote(mappedData)
        }
    }

    private fun onNoteClick(noteUiModel: NotesUiModel) {

    }


}