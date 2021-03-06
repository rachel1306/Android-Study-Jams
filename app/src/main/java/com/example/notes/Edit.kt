package com.gtappdevelopers.noteapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.notes.R
import com.example.notes.ViewModal

class Edit : AppCompatActivity() {
    lateinit var noteTitleEdt: EditText
    lateinit var noteEdt: EditText
    lateinit var saveBtn: Button
    lateinit var viewModal: ViewModal
    var noteID = -1;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit)
        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(ViewModal::class.java)
        noteTitleEdt = findViewById(R.id.title)
        noteEdt = findViewById(R.id.desc)
        saveBtn = findViewById(R.id.save)

        val noteType = intent.getStringExtra("noteType")
        if (noteType.equals("Edit")) {
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDescription = intent.getStringExtra("noteDescription")
            noteID = intent.getIntExtra("noteId", -1)
            saveBtn.setText("Update Note")
            noteTitleEdt.setText(noteTitle)
            noteEdt.setText(noteDescription)
        } else {
            saveBtn.setText("Save Note")
        }

//        saveBtn.setOnClickListener {
//            val noteTitle = noteTitleEdt.text.toString()
//            val noteDescription = noteEdt.text.toString()
//            if (noteType.equals("Edit")) {
//                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
//                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
//                    val currentDateAndTime: String = sdf.format(Date())
//                    val updatedNote = Note(noteTitle, noteDescription, currentDateAndTime)
//                    updatedNote.id = noteID
//                    viewModal.updateNote(updatedNote)
//                    Toast.makeText(this, "Note Updated..", Toast.LENGTH_LONG).show()
//                }
//            } else {
//                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
//                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
//                    val currentDateAndTime: String = sdf.format(Date())
//                    viewModal.addNote(Note(noteTitle, noteDescription, currentDateAndTime))
//                    Toast.makeText(this, "$noteTitle Added", Toast.LENGTH_LONG).show()
//                }
//            }
//            startActivity(Intent(applicationContext, MainActivity::class.java))
//            this.finish()
//        }
    }
}

