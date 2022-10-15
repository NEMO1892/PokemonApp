package com.example.pokemonapp.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.model.Result
import com.example.pokemonapp.repository.NetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListPokemonsViewModel(
    private val networkRepository: NetworkRepository
) : ViewModel() {

    val listPokemons = MutableLiveData<ArrayList<Result>>()

    fun getListPokemons(offset: Int? = null, limit: Int? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = networkRepository.getListPokemons(offset, limit)
            if (response.isSuccessful) {
                listPokemons.postValue(response.body()?.results)
            } else {
                response.errorBody()
            }
        }
    }
}