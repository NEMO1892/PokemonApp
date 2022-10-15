package com.example.pokemonapp.model

data class PokemonResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: ArrayList<Result>
)

data class Result(
    val name: String,
    val url: String
)