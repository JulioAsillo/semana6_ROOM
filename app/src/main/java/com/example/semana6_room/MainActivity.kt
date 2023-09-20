package com.example.semana6_room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.semana6_room.database.AppDatabase
import com.example.semana6_room.models.Contact

class MainActivity : AppCompatActivity() {

    lateinit var contacts: List<Contact>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadContent()
    }

    private fun loadContent() {
        contacts = AppDatabase.getInstance(this).getDao().getAll()
    }
}