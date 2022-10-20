package com.example.pokemonapp.ui.pokemon

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.model.Pokemon
import com.example.pokemonapp.model.PokemonLoadingState
import com.example.pokemonapp.repository.NetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonViewModel(
    private val networkRepository: NetworkRepository
) : ViewModel() {

    val onePokemon = MutableLiveData<Pokemon>()

    val pokemonLoadingStateLiveData = MutableLiveData<PokemonLoadingState>()

    fun getOnePokemon(id: Int? = 1) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                pokemonLoadingStateLiveData.postValue(PokemonLoadingState.LOADING)
                val response = networkRepository.getOnePokemon(id)
                if (response.isSuccessful) {
                    onePokemon.postValue(response.body())
                } else {
                    response.errorBody()
                }
                pokemonLoadingStateLiveData.postValue(PokemonLoadingState.LOADED)
            } catch (e: Exception) {
                pokemonLoadingStateLiveData.postValue(PokemonLoadingState.ERROR)
            }
        }
    }
}