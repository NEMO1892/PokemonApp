package com.example.pokemonapp.di

import androidx.paging.ExperimentalPagingApi
import com.example.pokemonapp.data.db.dao.ResultDao
import com.example.pokemonapp.data.network.PokemonApi
import com.example.pokemonapp.data.paging.PokemonRemoteMediator
import com.example.pokemonapp.data.repository.DbRepositoryImpl
import com.example.pokemonapp.data.repository.NetworkRepositoryImpl
import com.example.pokemonapp.domain.DbRepository
import com.example.pokemonapp.domain.NetworkRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoriesModule {

    @Provides
    fun provideNetworkRepository(
        api: PokemonApi
    ): NetworkRepository {
        return NetworkRepositoryImpl(api)
    }


    @Provides
    @Singleton
    @ExperimentalPagingApi
    fun provideDbRepository(
        resultDao: ResultDao,
        pokemonRemoteMediator: PokemonRemoteMediator
    ): DbRepository = DbRepositoryImpl(resultDao, pokemonRemoteMediator)
}