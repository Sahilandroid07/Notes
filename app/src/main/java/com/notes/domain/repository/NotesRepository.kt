package com.notes.domain.repository

import com.notes.domain.room.NotesDataEntity
import kotlinx.coroutines.flow.Flow

interface NotesRepository  {

    suspend fun getAllNotes(): Flow<List<NotesDataEntity>>

    suspend fun addAndUpdateNotes(notesData: NotesDataEntity)

    suspend fun deleteNote(notesData: NotesDataEntity)
}