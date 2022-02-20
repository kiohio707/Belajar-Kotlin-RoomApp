package com.example.roomapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomapp.model.NoteModel

@Dao
interface NoteDao {
    @Insert
    fun insert(note: NoteModel)

    @Query("SELECT * FROM notes")
    fun getListNotes(): LiveData<List<NoteModel>>

    @Query("SELECT * FROM notes WHERE id=:noteId")
    fun getNote(noteId:String): LiveData<NoteModel>

    @Update
    fun updateNote(note: NoteModel)

    @Delete
    fun deleteNote(note: NoteModel): Int
}