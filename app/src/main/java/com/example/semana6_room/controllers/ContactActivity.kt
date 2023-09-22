package com.example.semana6_room.controllers

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.example.semana6_room.R
import com.example.semana6_room.database.AppDatabase
import com.example.semana6_room.models.Contact
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.Gson

class ContactActivity : AppCompatActivity() {
    lateinit var contact: Contact
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        val toolbar = findViewById<Toolbar>(R.id.tbContact)

        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        loadContact()
    }

    private fun loadContact() {
        val etName = findViewById<TextInputEditText>(R.id.etName)
        val etTelephone = findViewById<TextInputEditText>(R.id.etTelephone)

        //voy a recibir la informacion del cardView
        val gson = Gson()
        val stringObj = intent.getStringExtra("contact")

        contact = gson.fromJson(stringObj, Contact::class.java)?: Contact(null, "", "")

        if(contact.id != null){
            etName.setText(contact.nombre)
            etTelephone.setText(contact.telephone)
        }
    }

    fun saveContact(){
        //nuevo
        //editar
        val etName = findViewById<TextInputEditText>(R.id.etName)
        val etTelephone = findViewById<TextInputEditText>(R.id.etTelephone)

        //val name = etName.text.toString()
        //val telephone = etTelephone.text.toString()

        contact.nombre = etName.text.toString()
        contact.telephone = etTelephone.text.toString()

        if (contact.id == null){
            AppDatabase.getInstance(this).getDao().insertContact(contact)
        }else{
            AppDatabase.getInstance(this).getDao().updateContact(contact)
        }

        finish()

        //contact = Contact(null, nombre, telephone)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.contact_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.itemSave -> {
                saveContact()
                true
            }

            R.id.itemDelete -> {
                deleteContact()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun deleteContact() {
        AppDatabase.getInstance(this).getDao().deleteContact(contact)
        finish()
    }


}