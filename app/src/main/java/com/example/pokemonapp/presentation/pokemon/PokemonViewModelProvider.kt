package com.example.pokemonapp.presentation.pokemon

import com.example.pokemonapp.domain.PokemonRepository
import com.example.pokemonapp.util.BaseViewModelFactory
import javax.inject.Inject

class PokemonViewModelProvider @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : BaseViewModelFactory<PokemonViewModel>(PokemonViewModel::class.java) {

    override fun createViewModel(): PokemonViewModel = PokemonViewModel(pokemonRepository)
}