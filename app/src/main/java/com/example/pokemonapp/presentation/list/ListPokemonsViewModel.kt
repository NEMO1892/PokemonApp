package com.example.pokemonapp.presentation.list

import androidx.lifecycle.ViewModel
import com.example.pokemonapp.data.repository.PokemonRepositoryImpl

const val PAGE_SIZE = 20

class ListPokemonsViewModel(
    pokemonRepository: PokemonRepositoryImpl
) : ViewModel() {

    val flow = pokemonRepository.getPokemons()
}