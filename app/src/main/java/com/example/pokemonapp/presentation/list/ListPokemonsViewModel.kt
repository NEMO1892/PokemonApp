package com.example.pokemonapp.presentation.list

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.pokemonapp.domain.DbRepository
import com.example.pokemonapp.domain.model.Result
import kotlinx.coroutines.Dispatchers

class ListPokemonsViewModel(
    dbRepository: DbRepository
) : ViewModel() {

    val flow: LiveData<PagingData<Result>> =
        dbRepository.getPokemons().asLiveData(context = Dispatchers.IO).cachedIn(viewModelScope)
}