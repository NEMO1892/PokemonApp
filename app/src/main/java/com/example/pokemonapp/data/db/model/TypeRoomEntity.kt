package com.example.pokemonapp.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "type")
data class TypeRoomEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "url")
    val url: String
)
