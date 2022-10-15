package com.example.pokemonapp.di

import com.example.pokemonapp.network.NetworkModule
import com.example.pokemonapp.ui.list.ListPokemonsFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class])
@Singleton
interface ApplicationComponent {

    fun inject(fragment: ListPokemonsFragment)
}