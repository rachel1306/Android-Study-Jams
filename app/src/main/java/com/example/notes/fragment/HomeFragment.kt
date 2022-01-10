package com.example.notes.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes.R
import com.example.notes.TodoAdapter
import com.example.notes.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.gtappdevelopers.noteapplication.Note
import com.gtappdevelopers.noteapplication.NoteClickDeleteInterface
import com.gtappdevelopers.noteapplication.NoteClickInterface
import com.gtappdevelopers.noteapplication.ViewModal

class HomeFragment : Fragment(R.layout.activity_main), NoteClickDeleteInterface,
    NoteClickInterface {
    lateinit var viewModal: ViewModal
    lateinit var addFAB: FloatingActionButton

    val binding by lazy { ActivityMainBinding.bind(requireView()) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.notesRV.layoutManager = LinearLayoutManager(requireContext())
        val noteRVAdapter = TodoAdapter({
            onNoteClick(it)
        }, {
            onDeleteIconClick(it)
        })
        binding.notesRV.adapter = noteRVAdapter
        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        ).get(ViewModal::class.java)
        viewModal.allNotes.observe(viewLifecycleOwner, Observer { list ->
            noteRVAdapter.submitList(list)
        })
        binding.idFAB.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToAddFragment2(
                    false
                )
            )
        }
    }

    override fun onNoteClick(note: Note) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToAddFragment2(
                true,
                note
            )
        )
    }

    override fun onDeleteIconClick(note: Note) {
        viewModal.deleteNote(note)
        Toast.makeText(requireContext(), "${note.noteTitle} Deleted", Toast.LENGTH_LONG).show()
    }
}