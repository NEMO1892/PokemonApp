package com.example.pokemonapp.domain

import androidx.paging.PagingSource
import com.example.pokemonapp.data.db.model.RemoteKeys
import com.example.pokemonapp.data.db.model.ResultRoomEntity
import com.example.pokemonapp.data.network.model.PokemonNetworkEntity
import com.example.pokemonapp.data.network.model.PokemonResponseNetworkEntity
import com.example.pokemonapp.data.repository.PAGE_SIZE
import retrofit2.Response

interface PokemonRepository {

    suspend fun getListPokemons(
        offset: Int? = 0,
        limit: Int? = PAGE_SIZE
    ): Response<PokemonResponseNetworkEntity>

    suspend fun getOnePokemon(id: Int? = 1): Response<PokemonNetworkEntity>

    suspend fun insertResults(results: ArrayList<ResultRoomEntity>?)

    fun getAllResults(): PagingSource<Int, ResultRoomEntity>

    suspend fun clearAllResults()

    suspend fun getRemoteKey(nameId: String): RemoteKeys

    suspend fun addAllRemoteKeys(remoteKeys: List<RemoteKeys>?)

    suspend fun deleteAllRemoteKeys()
}