package com.example.pokemonapp.data.repository

import androidx.paging.*
import com.example.pokemonapp.data.db.model.RemoteKeys
import com.example.pokemonapp.data.db.model.ResultRoomEntity
import com.example.pokemonapp.domain.PokemonRepository
import com.example.pokemonapp.domain.RemoteMediatorRepository
import com.example.pokemonapp.util.mapResultFromNetworkEntityToRoomEntity
import javax.inject.Inject

const val PAGE_SIZE = 20

@ExperimentalPagingApi
class RemoteMediatorRepositoryImpl @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : RemoteMediatorRepository {

    override suspend fun fetchPokemons(offset: Int): ArrayList<ResultRoomEntity>? {
        val response = pokemonRepository.getListPokemons(offset = offset)
        return response.body()?.results?.mapResultFromNetworkEntityToRoomEntity()
    }

    override suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, ResultRoomEntity>): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.name?.let { id ->
                pokemonRepository.getRemoteKey(nameId = id)
            }
        }
    }

    override suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, ResultRoomEntity>): RemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { result ->
                pokemonRepository.getRemoteKey(nameId = result.name)
            }
    }

    override suspend fun getRemoteKeyForLastItem(state: PagingState<Int, ResultRoomEntity>): RemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { result ->
                pokemonRepository.getRemoteKey(nameId = result.name)
            }
    }
}