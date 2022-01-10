package com.example.notes

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.*
import com.gtappdevelopers.noteapplication.Note
import com.gtappdevelopers.noteapplication.rep
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModal(
    @SuppressLint("StaticFieldLeak") val context: Context
) : ViewModel() {
    val allNotes: LiveData<List<Note>>
    val repository: rep
    private val _query = MutableLiveData<String?>(null)
    val query: LiveData<String?>
        get() = _query

    init {
        val dao = db.getDatabase(context).getNotesDao()
        repository = rep(dao)
        allNotes = query.switchMap {
            repository.getAllNotes(it).asLiveData()
        }

    }

    fun onQueryChange(query: String?) {
        _query.postValue(query)
    }

    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)
    }

    fun addNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
}

