package com.example.pokemonapp.network

import com.example.pokemonapp.model.Pokemon
import com.example.pokemonapp.model.PokemonResponse
import com.example.pokemonapp.ui.list.PAGE_SIZE
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {

    @GET("/api/v2/pokemon")
    suspend fun getPokemons(
        @Query("offset") offset: Int? = 0,
        @Query("limit") limit: Int? = PAGE_SIZE
    ): Response<PokemonResponse>

    @GET("/api/v2/pokemon/{id}")
    suspend fun getOnePokemon(
        @Path("id") id: Int? = 1
    ): Response<Pokemon>
}