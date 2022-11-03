package com.example.pokemonapp.data.repository

import androidx.paging.*
import com.example.pokemonapp.data.db.AppDataBase
import com.example.pokemonapp.domain.model.Result
import com.example.pokemonapp.data.paging.PokemonRemoteMediator
import com.example.pokemonapp.domain.PokemonRepository
import com.example.pokemonapp.presentation.list.PAGE_SIZE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class PokemonRepositoryImpl @Inject constructor(
    private val appDatabase: AppDataBase,
    private val networkRepository: NetworkRepositoryImpl
) : PokemonRepository {

    override fun getPokemons(): Flow<PagingData<Result>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                initialLoadSize = PAGE_SIZE * 3
            ),
            remoteMediator = PokemonRemoteMediator(networkRepository, appDatabase),
            pagingSourceFactory = { appDatabase.getResultDao().getAllResults() }
        )
            .flow
            .map { pagingData ->
                pagingData.map { resultRoomEntity ->
                    Result(
                        resultRoomEntity.name,
                        resultRoomEntity.url
                    )
                }
            }
    }
}