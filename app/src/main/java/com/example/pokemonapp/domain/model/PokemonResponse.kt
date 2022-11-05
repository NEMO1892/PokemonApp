package com.example.pokemonapp.domain.model

data class Result(
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