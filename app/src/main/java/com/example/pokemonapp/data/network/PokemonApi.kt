package com.example.pokemonapp.data.network

import com.example.pokemonapp.data.network.model.PokemonNetworkEntity
import com.example.pokemonapp.data.network.model.PokemonResponseNetworkEntity
import com.example.pokemonapp.presentation.list.PAGE_SIZE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {

    @GET("/api/v2/pokemon")
    suspend fun getPokemons(
        @Query("offset") offset: Int? = 0,
        @Query("limit") limit: Int? = PAGE_SIZE
    ): Response<PokemonResponseNetworkEntity>

    @GET("/api/v2/pokemon/{id}")
    suspend fun getOnePokemon(
        @Path("id") id: Int? = 1
    ): Response<PokemonNetworkEntity>
}