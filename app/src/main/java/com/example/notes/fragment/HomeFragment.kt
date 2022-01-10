package com.example.notes.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.gtappdevelopers.noteapplication.*

class HomeFragment: Fragment(R.layout.activity_main), NoteClickDeleteInterface, NoteClickInterface {
    lateinit var viewModal: ViewModal
    lateinit var notesRV: RecyclerView
    lateinit var addFAB: FloatingActionButton

    val binding  by lazy{ ActivityMainBinding.bind(requireView())}
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notesRV.layoutManager = LinearLayoutManager(requireContext())
        val noteRVAdapter = NoteRVAdapter(requireContext(), this, this)
        notesRV.adapter = noteRVAdapter
        viewModal = ViewModelProvider(
                this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        ).get(ViewModal::class.java)
        viewModal.allNotes.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                noteRVAdapter.updateList(it)
            }
        })
        addFAB.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAddFragment2(false))
        }
    }
    override fun onNoteClick(note: Note) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAddFragment2(true, note))
    }

    override fun onDeleteIconClick(note: Note) {
        viewModal.deleteNote(note)
        Toast.makeText(requireContext(), "${note.noteTitle} Deleted", Toast.LENGTH_LONG).show()
    }
}