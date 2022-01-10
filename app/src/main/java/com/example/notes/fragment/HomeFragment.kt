package com.example.notes.fragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes.R
import com.example.notes.TodoAdapter
import com.example.notes.ViewModal
import com.example.notes.ViewModalFactory
import com.example.notes.databinding.ActivityMainBinding
import com.gtappdevelopers.noteapplication.Note
import com.gtappdevelopers.noteapplication.NoteClickDeleteInterface
import com.gtappdevelopers.noteapplication.NoteClickInterface

class HomeFragment : Fragment(R.layout.activity_main), NoteClickDeleteInterface,
    NoteClickInterface {
    val viewModal by activityViewModels<ViewModal> {
        ViewModalFactory(requireContext().applicationContext)
    }
    private lateinit var searchView: SearchView
    val noteRVAdapter = TodoAdapter({
        onNoteClick(it)
    }, {
        onDeleteIconClick(it)
    })

    //    val binding by lazy { ActivityMainBinding.bind(requireView()) }
    private lateinit var binding: ActivityMainBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ActivityMainBinding.bind(requireView())
        binding.notesRV.apply {
            adapter = noteRVAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
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
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search, menu)

        val searchItem = menu.findItem(R.id.search)
        searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query.isNullOrBlank()) {
                    Toast.makeText(requireContext(), "Empty Query", Toast.LENGTH_LONG).show()
                }
                viewModal.onQueryChange(query)
                return true

            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })

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