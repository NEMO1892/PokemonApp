package com.example.pokemonapp.data.repository

import androidx.paging.*
import com.example.pokemonapp.data.db.dao.ResultDao
import com.example.pokemonapp.data.paging.PokemonRemoteMediator
import com.example.pokemonapp.domain.PagerRepository
import com.example.pokemonapp.domain.model.Result
import com.example.pokemonapp.util.mapResultFromRoomEntityToDomainEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalPagingApi
class PagerRepositoryImpl
@Inject constructor(
    private val resultDao: ResultDao,
    private val pokemonRemoteMediator: PokemonRemoteMediator
) : PagerRepository {

    override fun getPokemons(): Flow<PagingData<Result>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                initialLoadSize = PAGE_SIZE * 3
            ),
            remoteMediator = pokemonRemoteMediator,
            pagingSourceFactory = { resultDao.getAllResults() }
        )
            .flow.mapResultFromRoomEntityToDomainEntity()
    }
}