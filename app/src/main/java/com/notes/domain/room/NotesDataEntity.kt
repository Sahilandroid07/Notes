package com.notes.domain.room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "notes")
data class NotesDataEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val createdAt: Long?,
    val updatedAt: Long?,
    val backgroundColor: String?,
    val textColor: String?
)