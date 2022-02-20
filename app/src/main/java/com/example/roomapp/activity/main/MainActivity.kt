package com.example.roomapp.activity.main

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapp.adapter.NoteAdapter
import com.example.roomapp.activity.createnote.CreateNoteActivity
import com.example.roomapp.activity.createnote.NoteViewModel
import com.example.roomapp.activity.editnote.EditNoteActivity
import com.example.roomapp.databinding.ActivityMainBinding
import com.example.roomapp.model.NoteModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: NoteViewModel
    lateinit var adapter: NoteAdapter

    companion object {
        const val CREATE_NOTE_REQUEST_CODE = 1
        const val UPDATE_NOTE_REQUEST_CODE = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = NoteAdapter()
        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        showListNote()
        binding.fabAddMain.setOnClickListener {
            Intent(this, CreateNoteActivity::class.java).also {
                startActivityForResult(it, CREATE_NOTE_REQUEST_CODE)
            }
        }
    }

    private fun showListNote() {
        binding.apply {
            recViewMain.setHasFixedSize(true)
            recViewMain.layoutManager = LinearLayoutManager(this@MainActivity)
            viewModel.getListNotes().observe(this@MainActivity, object : Observer<List<NoteModel>> {
                override fun onChanged(t: List<NoteModel>?) {
                    if (t != null) {
                        adapter.setListNote(t)
                        recViewMain.adapter  = adapter
                    }
                }

            })
            adapter.setOnItemCLickCallbak(object : NoteAdapter.OnItemClickCallback {
                override fun onItemClicked(noteModel: NoteModel) {
                    Intent(this@MainActivity, EditNoteActivity::class.java).also {
                        it.putExtra(EditNoteActivity.NOTE_ID, noteModel.id)
                        startActivityForResult(it, UPDATE_NOTE_REQUEST_CODE)
                    }
                }

            })
        }
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.delete(adapter.getNoteAt(viewHolder.layoutPosition))
                Toast.makeText(this@MainActivity, "Note Berhasil Dihapus", Toast.LENGTH_SHORT).show()
            }

        }).attachToRecyclerView(binding.recViewMain)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CREATE_NOTE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val note = data?.getStringExtra(CreateNoteActivity.NEW_NOTE)
            if (note != null) {
                viewModel.insert(note)
            }
            Toast.makeText(this, "Note Berhasil Ditambahkan", Toast.LENGTH_SHORT).show()
        } else if (requestCode == UPDATE_NOTE_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            val noteId = data?.getStringExtra(EditNoteActivity.NOTE_ID)
            val updatedNote = data?.getStringExtra(EditNoteActivity.UPDATED_NOTE)

            val note = noteId?.let { updatedNote?.let { it1 -> NoteModel(it, it1) } }

            viewModel.update(note!!)

            Toast.makeText(this, "Note Berhasil Diperbarui", Toast.LENGTH_SHORT).show()
        }
    }


}