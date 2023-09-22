package com.example.semana6_room.controllers

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.semana6_room.R
import com.example.semana6_room.adapters.ContactAdapter
import com.example.semana6_room.adapters.OnItemClickListener
import com.example.semana6_room.database.AppDatabase
import com.example.semana6_room.models.Contact
import com.google.gson.Gson

class MainActivity : AppCompatActivity(), OnItemClickListener {
    override fun OnItemClicked(contact: Contact) {
        ///vamos a enviar la información al Contact Activity
        val intent = Intent(this, ContactActivity::class.java)
        val gson = Gson()
        intent.putExtra("Contact", gson.toJson(contact))
        startActivity(intent)
    }

    lateinit var contacts: List<Contact>
    lateinit var contactAdapter: ContactAdapter


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.tbMain)

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onResume() {
        super.onResume()
        loadContent()

        val rvContact = findViewById<RecyclerView>(R.id.rvContact)
        contactAdapter = ContactAdapter(contacts, this)
        rvContact.adapter = contactAdapter
        rvContact.layoutManager = LinearLayoutManager(this)
    }

    private fun loadContent() {
        contacts = AppDatabase.getInstance(this).getDao().getAll()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.itemAdd -> {
                val intent = Intent(this, ContactActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}