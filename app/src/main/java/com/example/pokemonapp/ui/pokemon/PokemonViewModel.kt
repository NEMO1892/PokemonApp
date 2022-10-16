package com.example.pokemonapp.ui.pokemon

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.model.Pokemon
import com.example.pokemonapp.repository.NetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonViewModel(
    private val networkRepository: NetworkRepository
) : ViewModel() {

    val onePokemon = MutableLiveData<Pokemon>()

    fun getOnePokemon(id: Int? = 1) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = networkRepository.getOnePokemon(id)
            if (response.isSuccessful) {
                onePokemon.postValue(response.body())
            } else {
                response.errorBody()
            }
        }
    }
}