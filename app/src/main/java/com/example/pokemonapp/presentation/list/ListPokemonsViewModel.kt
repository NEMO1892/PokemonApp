package com.example.pokemonapp.presentation.list

import androidx.lifecycle.ViewModel
import com.example.pokemonapp.domain.PokemonRepository

const val PAGE_SIZE = 20

class ListPokemonsViewModel(
    pokemonRepository: PokemonRepository
) : ViewModel() {

    val flow = pokemonRepository.getPokemons()
}