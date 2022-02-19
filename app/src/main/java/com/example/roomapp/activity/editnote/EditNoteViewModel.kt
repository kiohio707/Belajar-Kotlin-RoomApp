package com.example.roomapp.activity.editnote

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.roomapp.database.NoteDao
import com.example.roomapp.database.NoteRoomDatabase
import com.example.roomapp.model.NoteModel

class EditNoteViewModel(application: Application): AndroidViewModel(application){
    private var noteDao: NoteDao?
    private var noteDb: NoteRoomDatabase?

    init {
        noteDb = NoteRoomDatabase.getDatabase(application)
        noteDao = noteDb?.noteDao()
    }

    fun getNote(noteId: String): LiveData<NoteModel>? = noteDao?.getNote(noteId)
}