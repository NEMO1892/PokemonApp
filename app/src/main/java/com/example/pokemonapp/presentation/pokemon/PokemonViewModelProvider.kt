package com.example.pokemonapp.presentation.pokemon

import com.example.pokemonapp.data.repository.NetworkRepositoryImpl
import com.example.pokemonapp.util.BaseViewModelFactory
import javax.inject.Inject

class PokemonViewModelProvider @Inject constructor(
    private val networkRepository: NetworkRepositoryImpl
) : BaseViewModelFactory<PokemonViewModel>(PokemonViewModel::class.java) {

    override fun createViewModel(): PokemonViewModel = PokemonViewModel(networkRepository)
}