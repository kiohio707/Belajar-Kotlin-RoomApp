package com.example.roomapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.roomapp.databinding.ItemNoteAdapterBinding
import com.example.roomapp.model.NoteModel

class NoteAdapter: RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private lateinit var listNote: List<NoteModel>

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemCLickCallbak(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setListNote(list: List<NoteModel>) {
        this.listNote = list

        notifyDataSetChanged()
    }

    fun getNoteAt(position: Int): NoteModel = listNote[position]

    inner class NoteViewHolder(val binding:ItemNoteAdapterBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(note: NoteModel) {
            with(binding) {
                tvNote.text = note.note
                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(note) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(ItemNoteAdapterBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(listNote[position])
    }

    override fun getItemCount(): Int {
        return listNote.size
    }

    interface OnItemClickCallback{
        fun onItemClicked(noteModel: NoteModel)
    }
}