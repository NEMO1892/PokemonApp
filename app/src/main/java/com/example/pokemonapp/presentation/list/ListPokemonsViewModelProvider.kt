package com.example.pokemonapp.presentation.list

import com.example.pokemonapp.data.repository.PokemonRepositoryImpl
import com.example.pokemonapp.util.BaseViewModelFactory
import javax.inject.Inject

class ListPokemonsViewModelProvider @Inject constructor(
    private val pokemonRepositoryImpl: PokemonRepositoryImpl
) : BaseViewModelFactory<ListPokemonsViewModel>(ListPokemonsViewModel::class.java) {

    override fun createViewModel(): ListPokemonsViewModel =
        ListPokemonsViewModel(pokemonRepositoryImpl)
}