package com.example.pokemonapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class PokemonResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: ArrayList<Result>
)

@Entity(tableName = "result")
data class Result(
    @PrimaryKey
    val name: String,
    val url: String
)

data class Pokemon(
    val name: String,
    val sprites: Sprites,
    val types: ArrayList<Types>,
    val weight: Double,
    val height: Double
)

data class Sprites(
    val front_default: String
)

data class Types(
    val slot: Int,
    val type: Type
)

data class Type(
    val name: String,
    val url: String
)