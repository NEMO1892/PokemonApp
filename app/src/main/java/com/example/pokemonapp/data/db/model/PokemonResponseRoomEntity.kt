package com.example.pokemonapp.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.pokemonapp.data.db.converter.PokemonResponseTypeConverter

@Entity(tableName = "pokemonResponse")
data class PokemonResponseRoomEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = 0,
    @ColumnInfo(name = "count")
    val count: Int,
    @ColumnInfo(name = "next")
    val next: String,
    @ColumnInfo(name = "previous")
    val previous: String,
    @ColumnInfo(name = "results")
    @TypeConverters(PokemonResponseTypeConverter::class)
    val results: ArrayList<ResultRoomEntity>
)