package com.example.pokemonapp.ui.pokemon

import com.example.pokemonapp.repository.NetworkRepository
import com.example.pokemonapp.ui.base.BaseViewModelFactory
import javax.inject.Inject

class PokemonViewModelProvider @Inject constructor(
    private val networkRepository: NetworkRepository
) : BaseViewModelFactory<PokemonViewModel>(PokemonViewModel::class.java) {

    override fun createViewModel(): PokemonViewModel = PokemonViewModel(networkRepository)
}