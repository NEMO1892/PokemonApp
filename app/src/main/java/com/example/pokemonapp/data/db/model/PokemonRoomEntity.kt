package com.example.pokemonapp.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.pokemonapp.data.db.converter.PokemonTypeConverter

@Entity(tableName = "pokemon")
@TypeConverters(PokemonTypeConverter::class)
data class PokemonRoomEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = 0,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "sprites")
    val sprites: SpritesRoomEntity,
    @ColumnInfo(name = "types")
    val types: ArrayList<TypesRoomEntity>,
    @ColumnInfo(name = "weight")
    val weight: Double,
    @ColumnInfo(name = "height")
    val height: Double
)
