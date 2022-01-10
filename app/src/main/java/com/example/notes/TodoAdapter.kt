package com.example.notes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.databinding.ItemBinding
import com.gtappdevelopers.noteapplication.Note


class TodoAdapter(private val onClick: (Note) -> Unit, private val onDelete: (Note) -> Unit) :
    ListAdapter<Note, TodoAdapter.ViewHolder>(TodoComparator) {
    object TodoComparator : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem

        }

    }

    inner class ViewHolder(val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            binding.note.text = note.noteTitle
            binding.date.text = note.timeStamp
            binding.root.setOnClickListener {
                onClick(note)
            }
            binding.delete.setOnClickListener {
                onDelete(note)
            }

        }
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }
}