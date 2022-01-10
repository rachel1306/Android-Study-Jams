package com.gtappdevelopers.noteapplication

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("Select * from notesTable order by id ASC")
    fun getAllNotes(): Flow<List<Note>>

    @Query("Select * from notesTable WHERE title LIKE '%' || :query || '%' order by id ASC")
    fun getAllNotes(query: String): Flow<List<Note>>

    @Update
    suspend fun update(note: Note)
}

