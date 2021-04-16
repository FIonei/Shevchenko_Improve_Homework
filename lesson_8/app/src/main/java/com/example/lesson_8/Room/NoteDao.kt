package com.example.lesson_8.Room

import androidx.room.*


@Dao
interface NoteDao {
    @Query("SELECT * FROM Notes")
    fun getAll(): List<NoteEntity?>?

    @Query("SELECT * FROM Notes WHERE id = :id")
    fun getById(id: Long): NoteEntity?

    @Query("SELECT * FROM Notes WHERE isArched = 0")
    fun getAllVisible(): List<NoteEntity?>?

    @Insert
    fun insert(noteEntity: NoteEntity?)

    @Update
    fun update(noteEntity: NoteEntity?)

    @Delete
    fun delete(noteEntity: NoteEntity?)
}