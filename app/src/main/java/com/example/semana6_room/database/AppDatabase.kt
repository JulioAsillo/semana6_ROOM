package com.example.semana6_room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.semana6_room.adapters.IContactDao
import com.example.semana6_room.models.Contact

@Database(entities = arrayOf(Contact::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    //usamos patron "singleton" para no abrir instancias de la BD
    abstract fun getDao(): IContactDao
    companion object{
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase{
            if(INSTANCE==null){
                //si no existe la BD, se crea la BD
                INSTANCE = Room
                    .databaseBuilder(context, AppDatabase::class.java, "mycontact.db")
                    .allowMainThreadQueries()
                    .build()
            }

            return INSTANCE as AppDatabase
        }
    }
}