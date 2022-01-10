package com.example.notes.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.notes.R
import com.example.notes.databinding.EditBinding
import com.gtappdevelopers.noteapplication.Note
import com.gtappdevelopers.noteapplication.ViewModal
import java.text.SimpleDateFormat
import java.util.*

class AddFragment : Fragment(R.layout.edit) {
    private val args by navArgs<AddFragmentArgs>()

    lateinit var viewModal: ViewModal
    var noteID = -1;
    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        EditBinding.bind(requireView())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        ).get(ViewModal::class.java)

        if (args.isEdit) {
            val noteTitle = args.todo?.noteTitle
            val noteDescription = args.todo?.noteDescription
            noteID = args.todo?.id ?: -1
            binding.save.setText("Update Note")
            binding.title.setText(noteTitle)
            binding.desc.setText(noteDescription)
        } else {
//            binding.setText("Save Note")
            binding.save.text = "Save Note"

        }

        binding.save.setOnClickListener {
            val noteTitle = binding.title.text.toString()
            val noteDescription = binding.desc.text.toString()
            if (args.isEdit) {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime: String = sdf.format(Date())
                    val updatedNote =
                        Note(args.todo?.id ?: -1, noteTitle, noteDescription, currentDateAndTime)
                    updatedNote.id = noteID
                    viewModal.updateNote(updatedNote)
                    Toast.makeText(requireContext(), "Note Updated..", Toast.LENGTH_LONG).show()
                }
            } else {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime: String = sdf.format(Date())
                    viewModal.addNote(
                        Note(
                            noteTitle = noteTitle,
                            noteDescription = noteDescription,
                            timeStamp = currentDateAndTime
                        )
                    )
                    Toast.makeText(requireContext(), "$noteTitle Added", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}