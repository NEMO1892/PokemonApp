package com.example.pokemonapp.repository

import com.example.pokemonapp.network.PokemonApi
import javax.inject.Inject

class NetworkRepository @Inject constructor(
    private val api: PokemonApi
) {

    suspend fun getListPokemons(offset: Int? = null, limit: Int? = null) =
        api.getPokemons(offset = offset, limit = limit)
}