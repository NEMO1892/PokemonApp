package com.example.pokemonapp.presentation.list

import com.example.pokemonapp.domain.PagerRepository
import com.example.pokemonapp.util.BaseViewModelFactory
import javax.inject.Inject

class ListPokemonsViewModelProvider @Inject constructor(
    private val pagerRepository: PagerRepository
) : BaseViewModelFactory<ListPokemonsViewModel>(ListPokemonsViewModel::class.java) {

    override fun createViewModel(): ListPokemonsViewModel =
        ListPokemonsViewModel(pagerRepository)
}