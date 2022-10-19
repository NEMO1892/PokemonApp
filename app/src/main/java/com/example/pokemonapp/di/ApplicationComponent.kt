package com.example.pokemonapp.di

import com.example.pokemonapp.network.NetworkModule
import com.example.pokemonapp.ui.list.ListPokemonsFragment
import com.example.pokemonapp.ui.pokemon.PokemonFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class, DatabaseModule::class])
@Singleton
interface ApplicationComponent {

    fun inject(fragment: ListPokemonsFragment)

    fun inject(fragment: PokemonFragment)
}