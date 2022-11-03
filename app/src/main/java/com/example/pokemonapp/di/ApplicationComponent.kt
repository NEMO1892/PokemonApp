package com.example.pokemonapp.di

import com.example.pokemonapp.presentation.list.ListPokemonsFragment
import com.example.pokemonapp.presentation.pokemon.PokemonFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class, DatabaseModule::class])
@Singleton
interface ApplicationComponent {

    fun inject(fragment: ListPokemonsFragment)

    fun inject(fragment: PokemonFragment)
}