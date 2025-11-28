package com.notes.domain.repository

import com.notes.domain.room.NotesDao
import com.notes.domain.room.NotesDataEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(
    private val dao: NotesDao
): NotesRepository {
    override suspend fun getAllNotes(): Flow<List<NotesDataEntity>> = dao.getAllNotes()

    override suspend fun addAndUpdateNotes(notesData: NotesDataEntity) = dao.addAndUpdateNotes(notesData)

    override suspend fun deleteNote(notesData: NotesDataEntity) = dao.deleteNotes(notesData)
}