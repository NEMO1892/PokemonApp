package com.example.pokemonapp.presentation.list

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.pokemonapp.domain.PagerRepository
import com.example.pokemonapp.domain.model.Result
import kotlinx.coroutines.Dispatchers

class ListPokemonsViewModel(
    pagerRepository: PagerRepository
) : ViewModel() {

    val flow: LiveData<PagingData<Result>>? =
        pagerRepository.getPokemons()?.asLiveData(context = Dispatchers.IO)?.cachedIn(viewModelScope)
}