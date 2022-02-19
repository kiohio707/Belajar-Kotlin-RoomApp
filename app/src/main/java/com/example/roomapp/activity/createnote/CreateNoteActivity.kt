package com.example.roomapp.activity.createnote

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.example.roomapp.R
import com.example.roomapp.databinding.ActivityCreateNoteBinding

class CreateNoteActivity : AppCompatActivity() {
    lateinit var binding: ActivityCreateNoteBinding

    companion object {
        val NEW_NOTE = "new_note"
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityCreateNoteBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnNotesCreate.setOnClickListener {
            val resultIntent = Intent()

            if (!TextUtils.isEmpty(binding.edtNote.text)) {
                val note = binding.edtNote.text.toString()

                resultIntent.putExtra(NEW_NOTE, note)
                setResult(Activity.RESULT_OK, resultIntent)
            } else {
                setResult(Activity.RESULT_CANCELED)
            }
            finish()
        }
    }
}