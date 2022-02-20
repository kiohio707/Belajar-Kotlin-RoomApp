package com.example.roomapp.activity.createnote

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.roomapp.database.NoteDao
import com.example.roomapp.database.NoteRoomDatabase
import com.example.roomapp.model.NoteModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class NoteViewModel(application: Application): AndroidViewModel(application) {
    private var noteDao: NoteDao?
    private var noteDb: NoteRoomDatabase?
    private var listNote: LiveData<List<NoteModel>>

    init {
        noteDb = NoteRoomDatabase.getDatabase(application)
        noteDao = noteDb?.noteDao()
        listNote = noteDao!!.getListNotes()
    }

    fun insert(textNote: String) {
        insertToDatabase(textNote)
    }

    fun getListNotes(): LiveData<List<NoteModel>> {
        return listNote
    }

    private fun insertToDatabase(textNote: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val noteId = UUID.randomUUID().toString()
            val note = NoteModel(
                noteId,
                textNote
            )

            noteDao?.insert(note)
        }
    }

    fun update(note: NoteModel) {
        CoroutineScope(Dispatchers.IO).launch {
            noteDao?.updateNote(note)
        }
    }

    fun delete(note: NoteModel) {
        CoroutineScope(Dispatchers.IO).launch {
            noteDao?.deleteNote(note)
        }
    }

}