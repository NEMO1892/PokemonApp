package com.example.pokemonapp.repository

import com.example.pokemonapp.network.PokemonApi
import javax.inject.Inject

class NetworkRepository @Inject constructor(
    private val api: PokemonApi
) {

    suspend fun getListPokemons(offset: Int? = 0, limit: Int? = 20) =
        api.getPokemons(offset = offset, limit = limit)
}