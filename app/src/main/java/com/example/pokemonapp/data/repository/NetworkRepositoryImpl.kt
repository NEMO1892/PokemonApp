package com.example.pokemonapp.data.repository

import com.example.pokemonapp.data.network.PokemonApi
import com.example.pokemonapp.domain.NetworkRepository
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val api: PokemonApi
) : NetworkRepository {

    override suspend fun getListPokemons(offset: Int?, limit: Int?) =
        api.getPokemons(offset = offset, limit = limit)

    override suspend fun getOnePokemon(id: Int?) =
        api.getOnePokemon(id = id)
}