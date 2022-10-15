package com.example.pokemonapp.ui.list

import com.example.pokemonapp.repository.NetworkRepository
import com.example.pokemonapp.repository.PokemonDataSource
import com.example.pokemonapp.ui.base.BaseViewModelFactory
import javax.inject.Inject

class ListPokemonsViewModelProvider @Inject constructor(
    private val dataSource: PokemonDataSource
) : BaseViewModelFactory<ListPokemonsViewModel>(ListPokemonsViewModel::class.java) {

    override fun createViewModel(): ListPokemonsViewModel =
        ListPokemonsViewModel(dataSource)
}