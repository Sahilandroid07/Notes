package com.notes.domain.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NotesRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindNotesRepository(
        impl: NotesRepositoryImpl
    ): NotesRepository
}