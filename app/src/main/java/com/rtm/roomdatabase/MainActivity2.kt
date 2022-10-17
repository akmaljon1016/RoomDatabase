package com.rtm.roomdatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rtm.roomdatabase.NoteDatabase.DatabaseBuilder.getDatabase
import com.rtm.roomdatabase.databinding.ActivityMain2Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSave.setOnClickListener {
            val title=binding.edTitle.text.toString()
            val desc=binding.edDesc.text.toString()
            val note=Note(0,title,desc)
            GlobalScope.launch(Dispatchers.IO) {
                NoteDatabase.DatabaseBuilder.getDatabase(this@MainActivity2)
                    .noteDao()
                    .saveNote(note)
            }
            finish()
        }
    }
}