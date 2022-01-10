package com.gtappdevelopers.noteapplication

import androidx.lifecycle.LiveData

class rep(private val notesDao: NotesDao) {
    val allNotes = notesDao.getAllNotes()

    suspend fun insert(note: Note) {
        notesDao.insert(note)
    }
    suspend fun delete(note: Note){
        notesDao.delete(note)
    }
    suspend fun update(note: Note){
        notesDao.update(note)
    }
}

