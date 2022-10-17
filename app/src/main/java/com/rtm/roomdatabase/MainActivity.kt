package com.rtm.roomdatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rtm.roomdatabase.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var itemAdapter: ItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        itemAdapter = ItemAdapter()

        binding.btnAdd.setOnClickListener {
            startActivity(Intent(this, MainActivity2::class.java))
        }
        rec()
    }

    override fun onResume() {
        super.onResume()
        rec()
    }

    fun rec() {
        GlobalScope.launch(Dispatchers.IO) {
            val list: List<Note> = NoteDatabase.DatabaseBuilder
                .getDatabase(this@MainActivity)
                .noteDao().getAllNotes()
            itemAdapter.submitList(list)
        }
        binding.recyclerview.adapter = itemAdapter
    }
}