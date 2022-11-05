package com.example.pokemonapp.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "resultRoom")
data class ResultRoomEntity(
    @PrimaryKey
    val name: String,
    @ColumnInfo(name = "url")
    val url: String
)
