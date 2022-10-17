package com.rtm.roomdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDao {

    @Insert
    fun saveNote(note: Note)

    @Query("SELECT * FROM note_table")
    fun getAllNotes():List<Note>
}