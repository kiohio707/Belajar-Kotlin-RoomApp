package com.example.roomapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomapp.model.NoteModel

@Database(entities = [NoteModel::class], version = 1)
abstract class NoteRoomDatabase: RoomDatabase() {
   abstract fun noteDao() : NoteDao

    companion object {
        var INSTANCE : NoteRoomDatabase? = null

        fun getDatabase(context: Context): NoteRoomDatabase? {
            if(INSTANCE == null) {
                synchronized(NoteRoomDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, NoteRoomDatabase::class.java, "note_database").build()
                }
            }
            return INSTANCE
        }
    }
}