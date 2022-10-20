package com.example.pokemonapp.repository

import com.example.pokemonapp.network.PokemonApi
import com.example.pokemonapp.ui.list.PAGE_SIZE
import javax.inject.Inject

class NetworkRepository @Inject constructor(
    private val api: PokemonApi
) {

    suspend fun getListPokemons(offset: Int? = 0, limit: Int? = PAGE_SIZE) =
        api.getPokemons(offset = offset, limit = limit)

    suspend fun getOnePokemon(id: Int? = 1) =
        api.getOnePokemon(id = id)
}