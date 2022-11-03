package com.example.pokemonapp.presentation.pokemon

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.domain.model.Pokemon
import com.example.pokemonapp.domain.model.PokemonLoadingState
import com.example.pokemonapp.data.repository.NetworkRepositoryImpl
import com.example.pokemonapp.domain.model.Type
import com.example.pokemonapp.domain.model.Types
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonViewModel(
    private val networkRepository: NetworkRepositoryImpl
) : ViewModel() {

    val onePokemon = MutableLiveData<Pokemon>()

    val pokemonLoadingStateLiveData = MutableLiveData<PokemonLoadingState>()

    fun getOnePokemon(id: Int? = 1) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                pokemonLoadingStateLiveData.postValue(PokemonLoadingState.LOADING)
                val response = networkRepository.getOnePokemon(id)
                if (response.isSuccessful) {
                    onePokemon.postValue(response.body()?.let {
                        Pokemon(
                            name = it.name,
                            sprites = it.sprites,
                            types = it.types.map { types ->
                                Types(
                                    slot = types.slot,
                                    type = Type(
                                        name = types.type.name,
                                        url = types.type.url
                                    )
                                )
                            } as ArrayList<Types>,
                            weight = it.weight,
                            height = it.height
                        )
                    })
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