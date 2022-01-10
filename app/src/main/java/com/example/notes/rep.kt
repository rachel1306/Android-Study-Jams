package com.gtappdevelopers.noteapplication

import kotlinx.coroutines.flow.Flow

class rep(private val notesDao: NotesDao) {
    val allNotes = notesDao.getAllNotes()

    fun getAllNotes(query: String? = null): Flow<List<Note>> {
        return if (query.isNullOrBlank() || query.isEmpty()) {
            notesDao.getAllNotes()
        } else {
            notesDao.getAllNotes(query)
        }
    }

    suspend fun insert(note: Note) {
        notesDao.insert(note)
    }

    suspend fun delete(note: Note) {
        notesDao.delete(note)
    }

    suspend fun update(note: Note) {
        notesDao.update(note)
    }
}

