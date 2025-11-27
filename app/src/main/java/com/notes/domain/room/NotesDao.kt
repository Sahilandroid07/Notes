package com.notes.domain.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow


@Dao
interface NotesDao {

    @Upsert
    suspend fun addAndUpdateNotes(notesData: NotesDataEntity)

    @Delete
    suspend fun deleteNotes(notesData: NotesDataEntity)

    @Query("SELECT * FROM notes ORDER BY createdAt DESC")
    fun getAllNotes(): Flow<List<NotesDataEntity>>

}