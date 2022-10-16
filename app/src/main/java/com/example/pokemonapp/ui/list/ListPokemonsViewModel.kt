package com.example.pokemonapp.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.pokemonapp.repository.PokemonDataSource

const val COUNT_ITEM = 20

class ListPokemonsViewModel(
    private val dataSource: PokemonDataSource
) : ViewModel() {

    val flow = Pager(PagingConfig(pageSize = COUNT_ITEM, initialLoadSize = COUNT_ITEM)) {
        dataSource
    }.flow.cachedIn(viewModelScope)
}