package com.example.pokemonapp.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.pokemonapp.data.db.model.ResultRoomEntity
import com.example.pokemonapp.data.db.model.RemoteKeys
import com.example.pokemonapp.data.repository.PAGE_SIZE
import com.example.pokemonapp.domain.PokemonRepository
import com.example.pokemonapp.domain.RemoteMediatorRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@ExperimentalPagingApi
class PokemonRemoteMediator @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    private val remoteMediatorRepository: RemoteMediatorRepository
) : RemoteMediator<Int, ResultRoomEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ResultRoomEntity>
    ): MediatorResult {

        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKey =
                        remoteMediatorRepository.getRemoteKeyClosestToCurrentPosition(state)
                    remoteKey?.nextPage?.minus(1) ?: 0
                }
                LoadType.PREPEND -> {
                    val remoteKeys = remoteMediatorRepository.getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = remoteMediatorRepository.getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    nextPage
                }
            }

            val response = pokemonRepository.getListPokemons(offset = currentPage * PAGE_SIZE)
            val endOfPaginationReached = response.body()?.count == currentPage

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            if (loadType == LoadType.REFRESH) {
                pokemonRepository.clearAllResults()
                pokemonRepository.deleteAllRemoteKeys()
            }
            val keys = response.body()?.results?.map { result ->
                RemoteKeys(
                    id = result.name,
                    prevPage = prevPage,
                    nextPage = nextPage
                )
            }
            pokemonRepository.addAllRemoteKeys(keys)
            pokemonRepository.insertResults(remoteMediatorRepository.fetchPokemons(currentPage * PAGE_SIZE))

            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }
}