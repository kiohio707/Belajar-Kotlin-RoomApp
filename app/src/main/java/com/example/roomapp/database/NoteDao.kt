package com.example.roomapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
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
}