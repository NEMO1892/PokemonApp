package com.example.pokemonapp.di

import androidx.paging.ExperimentalPagingApi
import com.example.pokemonapp.data.db.AppDataBase
import com.example.pokemonapp.data.db.dao.ResultDao
import com.example.pokemonapp.data.network.PokemonApi
import com.example.pokemonapp.data.paging.PokemonRemoteMediator
import com.example.pokemonapp.data.repository.NetworkRepositoryImpl
import com.example.pokemonapp.data.repository.PokemonRepositoryImpl
import com.example.pokemonapp.domain.NetworkRepository
import com.example.pokemonapp.domain.PokemonRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoriesModule {

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun providePokemonRepository(
        resultDao: ResultDao,
        pokemonRemoteMediator: PokemonRemoteMediator
    ): PokemonRepository {
        return PokemonRepositoryImpl(
            resultDao, pokemonRemoteMediator
        )
    }

    @Provides
    @Singleton
    fun provideNetworkRepository(
        api: PokemonApi
    ): NetworkRepository {
        return NetworkRepositoryImpl(api)
    }
}