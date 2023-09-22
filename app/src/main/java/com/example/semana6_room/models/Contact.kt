package com.example.semana6_room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity
class Contact (
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @ColumnInfo (name="Nombre")
    @NotNull
    var nombre: String,
    @ColumnInfo (name="Telephone")
    @NotNull
    var telephone: String
)