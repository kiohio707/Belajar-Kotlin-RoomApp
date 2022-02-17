package com.example.roomapp

import androidx.room.Dao
import androidx.room.Insert
import com.example.roomapp.model.NoteModel

@Dao
interface NoteDao {
    @Insert
    fun insert(note: NoteModel)
}