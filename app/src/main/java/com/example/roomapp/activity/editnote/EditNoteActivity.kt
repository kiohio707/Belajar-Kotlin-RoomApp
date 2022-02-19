package com.example.roomapp.activity.editnote

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.roomapp.R
import com.example.roomapp.databinding.ActivityEditNoteBinding
import com.example.roomapp.model.NoteModel

class EditNoteActivity : AppCompatActivity() {
    lateinit var binding: ActivityEditNoteBinding
    lateinit var viewModel: EditNoteViewModel
    lateinit var note: LiveData<NoteModel>

    companion object {
        const val NOTE_ID = "note_id"
        const val UPDATED_NOTE = "updated_note"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEditNoteBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val id = intent.getStringExtra(NOTE_ID)

        viewModel = ViewModelProvider(this).get(EditNoteViewModel::class.java)
        note = id?.let { viewModel.getNote(it) }!!
        note.observe(this, object : Observer<NoteModel> {
            override fun onChanged(t: NoteModel?) {
                binding.apply {
                    edtNoteEdit.setText(t?.note)
                }
            }
        })

        binding.apply {
            btnNoteEdit.setOnClickListener {
                val updateNote = edtNoteEdit.text.toString()

                Intent().also {
                    it.putExtra(NOTE_ID, id)
                    it.putExtra(UPDATED_NOTE, updateNote)
                    setResult(Activity.RESULT_OK, it)
                    finish()
                }
            }

            btnCancelEdit.setOnClickListener {
                finish()
            }
        }
    }

}