package com.example.semana6_room.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity
class Contact (
    @PrimaryKey
    val id: Int,
    @ColumnInfo (name="Nombre")
    @NotNull
    val nombre: String,
    @ColumnInfo (name="Telephone")
    @NotNull
    val telephone: String
)