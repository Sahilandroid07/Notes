package com.notes.domain.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.notes.converter.BackgroundColorTypeConverter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import jakarta.inject.Singleton


@Database(
    entities = [NotesDataEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(BackgroundColorTypeConverter::class)
abstract class NotesDatabase: RoomDatabase() {
    abstract fun notesDao(): NotesDao
}
@Module
@InstallIn(SingletonComponent::class) // Lives as long as the app
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NotesDatabase {
        return Room.databaseBuilder(
            context,
            NotesDatabase::class.java,
            "NotesDatabase"
        ).build()
    }

    @Provides
    fun provideNotesDao(db: NotesDatabase): NotesDao = db.notesDao()
}