package com.example.notes.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notes.R
import com.example.notes.TempActivity
import com.example.notes.ViewModal
import com.example.notes.ViewModalFactory
import com.example.notes.databinding.EditBinding
import com.gtappdevelopers.noteapplication.Note
import java.text.SimpleDateFormat
import java.util.*

class AddFragment : Fragment(R.layout.edit) {
    private val args by navArgs<AddFragmentArgs>()

    val viewModal by activityViewModels<ViewModal> {
        ViewModalFactory(requireContext().applicationContext)
    }
    var noteID = -1;

    private lateinit var binding: EditBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = EditBinding.bind(requireView())
        if (args.isEdit) {
            val noteTitle = args.todo?.noteTitle
            val noteDescription = args.todo?.noteDescription
            noteID = args.todo?.id ?: -1
            binding.save.setText("Update Note")
            (activity as TempActivity).supportActionBar?.title = "Edit Note"
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
            findNavController().popBackStack()
        }
    }
}