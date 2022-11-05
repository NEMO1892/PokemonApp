package com.example.pokemonapp.presentation.pokemon

import com.example.pokemonapp.domain.NetworkRepository
import com.example.pokemonapp.util.BaseViewModelFactory
import javax.inject.Inject

class PokemonViewModelProvider @Inject constructor(
    private val networkRepository: NetworkRepository
) : BaseViewModelFactory<PokemonViewModel>(PokemonViewModel::class.java) {

    override fun createViewModel(): PokemonViewModel = PokemonViewModel(networkRepository)
}