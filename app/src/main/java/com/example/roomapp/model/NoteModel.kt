package com.example.roomapp.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteModel (
    @PrimaryKey
    @NonNull
    var id: String,

    @NonNull
    @ColumnInfo(name = "note")
    var note: String
)