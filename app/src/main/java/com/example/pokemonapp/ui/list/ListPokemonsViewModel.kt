package com.example.pokemonapp.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.pokemonapp.db.AppDataBase
import com.example.pokemonapp.repository.NetworkRepository
import com.example.pokemonapp.repository.PokemonRemoteMediator

const val PAGE_SIZE = 20

class ListPokemonsViewModel(
    private val appDatabase: AppDataBase,
    networkRepository: NetworkRepository
) : ViewModel() {

    @OptIn(ExperimentalPagingApi::class)
    val flow = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            initialLoadSize = PAGE_SIZE * 3
        ),
        remoteMediator = PokemonRemoteMediator(networkRepository, appDatabase),
        pagingSourceFactory = { appDatabase.getResultDao().getAllResults() }
    ).flow.cachedIn(viewModelScope)
}