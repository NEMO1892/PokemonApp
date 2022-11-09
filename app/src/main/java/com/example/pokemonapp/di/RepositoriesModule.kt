package com.example.pokemonapp.di

import androidx.paging.ExperimentalPagingApi
import com.example.pokemonapp.data.db.dao.RemoteKeysDao
import com.example.pokemonapp.data.db.dao.ResultDao
import com.example.pokemonapp.data.network.PokemonApi
import com.example.pokemonapp.data.paging.PokemonRemoteMediator
import com.example.pokemonapp.data.repository.PagerRepositoryImpl
import com.example.pokemonapp.data.repository.RemoteMediatorRepositoryImpl
import com.example.pokemonapp.data.repository.PokemonRepositoryImpl
import com.example.pokemonapp.domain.PagerRepository
import com.example.pokemonapp.domain.PokemonRepository
import com.example.pokemonapp.domain.RemoteMediatorRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoriesModule {

    @Provides
    @Singleton
    fun providePokemonRepository(
        api: PokemonApi,
        resultDao: ResultDao,
        remoteKeysDao: RemoteKeysDao
    ): PokemonRepository = PokemonRepositoryImpl(api, resultDao, remoteKeysDao)

    @Provides
    @Singleton
    @ExperimentalPagingApi
    fun providePagerRepository(
        resultDao: ResultDao,
        pokemonRemoteMediator: PokemonRemoteMediator
    ): PagerRepository = PagerRepositoryImpl(resultDao, pokemonRemoteMediator)

    @Provides
    @Singleton
    @ExperimentalPagingApi
    fun provideRemoteMediatorRepository(
        pokemonRepository: PokemonRepository
    ): RemoteMediatorRepository = RemoteMediatorRepositoryImpl(pokemonRepository)
}