package com.example.pokemonapp.network

import com.example.pokemonapp.model.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonApi {

    @GET("/api/v2/pokemon")
    suspend fun getPokemons(
        @Query("offset") offset: Int? = 0,
        @Query("limit") limit: Int? = 20
    ): Response<PokemonResponse>
}