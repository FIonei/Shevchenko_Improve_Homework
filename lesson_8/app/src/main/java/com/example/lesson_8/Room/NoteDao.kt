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

    @Query("UPDATE Notes SET isArched = 1 WHERE id = :id")
    fun archive(id: Long)

    @Query("DELETE FROM Notes WHERE id = :id")
    fun delete(id: Long)
}