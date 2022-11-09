package com.example.pokemonapp.presentation.pokemon

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.domain.PokemonRepository
import com.example.pokemonapp.domain.model.*
import com.example.pokemonapp.util.fromNetworkEntityToDomainEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonViewModel(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    val onePokemon = MutableLiveData<Pokemon>()

    val pokemonLoadingStateLiveData = MutableLiveData<PokemonLoadingState>()

    fun getOnePokemon(id: Int? = 1) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                pokemonLoadingStateLiveData.postValue(PokemonLoadingState.LOADING)
                val response = pokemonRepository.getOnePokemon(id)
                if (response.isSuccessful) {
                    onePokemon.postValue(response.body()?.fromNetworkEntityToDomainEntity())
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