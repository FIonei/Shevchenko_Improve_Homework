package com.example.lesson_8.Room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NoteEntity::class], version = 1)
abstract class NoteDatabase: RoomDatabase() {
    companion object{
        const val DATABASE_NAME = "Notes"
    }
    abstract fun noteDao(): NoteDao
}