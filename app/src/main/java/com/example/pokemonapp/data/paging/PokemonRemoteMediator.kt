package com.example.pokemonapp.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.pokemonapp.data.repository.NetworkRepositoryImpl
import com.example.pokemonapp.data.db.AppDataBase
import com.example.pokemonapp.data.db.model.ResultRoomEntity
import com.example.pokemonapp.data.db.model.RemoteKeys
import com.example.pokemonapp.domain.NetworkRepository
import com.example.pokemonapp.domain.model.Result
import com.example.pokemonapp.presentation.list.PAGE_SIZE
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@ExperimentalPagingApi
class PokemonRemoteMediator @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val appDataBase: AppDataBase
) : RemoteMediator<Int, Result>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Result>
    ): MediatorResult {

        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKey = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKey?.nextPage?.minus(1) ?: 0
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    nextPage
                }
            }

            val response = networkRepository.getListPokemons(offset = currentPage * PAGE_SIZE)
            val endOfPaginationReached = response.body()?.count == currentPage

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            appDataBase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    appDataBase.getResultDao().clearAllResults()
                    appDataBase.remoteKeysDao().deleteAllRemoteKeys()
                }
                val keys = response.body()?.results?.map { result ->
                    RemoteKeys(
                        id = result.name,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                appDataBase.remoteKeysDao().addAllRemoteKeys(keys)
//                appDataBase.getResultDao().insertResults(response.body()?.results?.map {
//                    ResultRoomEntity(
//                        name = it.name,
//                        url = it.url
//                    )
//                } as ArrayList<ResultRoomEntity>)
                appDataBase.getResultDao().insertResults(response.body()?.results?.map {
                    Result(
                        name = it.name,
                        url = it.url
                    )
                } as ArrayList<Result>)
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

//    private suspend fun fetchPokemons(
//        offset: Int
//    ): ArrayList<ResultRoomEntity> {
//        val response = networkRepository.getListPokemons(offset = offset)
//        return response.body()?.results?.map {
//            ResultRoomEntity(
//                name = it.name,
//                url = it.url
//            )
//        } as ArrayList<ResultRoomEntity>
//    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Result>
    ): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.name?.let { id ->
                appDataBase.remoteKeysDao().getRemoteKey(nameId = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Result>
    ): RemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { result ->
                appDataBase.remoteKeysDao().getRemoteKey(nameId = result.name)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Result>
    ): RemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { result ->
                appDataBase.remoteKeysDao().getRemoteKey(nameId = result.name)
            }
    }
}