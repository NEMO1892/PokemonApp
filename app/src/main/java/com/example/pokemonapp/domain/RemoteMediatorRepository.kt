package com.example.pokemonapp.domain

import androidx.paging.PagingState
import com.example.pokemonapp.data.db.model.RemoteKeys
import com.example.pokemonapp.data.db.model.ResultRoomEntity

interface RemoteMediatorRepository {

    suspend fun fetchPokemons(offset: Int): ArrayList<ResultRoomEntity>?

    suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, ResultRoomEntity>
    ): RemoteKeys?

    suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, ResultRoomEntity>
    ): RemoteKeys?

    suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, ResultRoomEntity>
    ): RemoteKeys?
}