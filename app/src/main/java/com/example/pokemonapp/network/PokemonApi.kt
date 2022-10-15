package com.example.pokemonapp.network

import com.example.pokemonapp.model.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonApi {

    @GET("/api/v2/pokemon")
    suspend fun getPokemons(
        @Query("offset") offset: Int? = null,
        @Query("limit") limit: Int? = null
    ): Response<PokemonResponse>
}