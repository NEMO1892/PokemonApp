package com.example.pokemonapp.domain

import com.example.pokemonapp.data.network.model.PokemonNetworkEntity
import com.example.pokemonapp.data.network.model.PokemonResponseNetworkEntity
import com.example.pokemonapp.data.repository.PAGE_SIZE
import retrofit2.Response

interface NetworkRepository {

    suspend fun getListPokemons(
        offset: Int? = 0,
        limit: Int? = PAGE_SIZE
    ): Response<PokemonResponseNetworkEntity>

    suspend fun getOnePokemon(id: Int? = 1): Response<PokemonNetworkEntity>
}