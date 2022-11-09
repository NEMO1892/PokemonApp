package com.example.pokemonapp.di

import android.app.Application
import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.room.Room
import com.example.pokemonapp.data.db.AppDataBase
import com.example.pokemonapp.data.db.dao.RemoteKeysDao
import com.example.pokemonapp.data.db.dao.ResultDao
import com.example.pokemonapp.data.paging.PokemonRemoteMediator
import com.example.pokemonapp.domain.PokemonRepository
import com.example.pokemonapp.domain.RemoteMediatorRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(private val context: Context, private val application: Application) {

    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Singleton
    @Provides
    fun provideDatabase(): AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideResultDao(database: AppDataBase): ResultDao = database.getResultDao()

    @Provides
    @Singleton
    fun provideRemoteKeysDao(database: AppDataBase): RemoteKeysDao = database.remoteKeysDao()

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun provideRemoteMediator(
        pokemonRepository: PokemonRepository,
        remoteMediatorRepository: RemoteMediatorRepository
    ): PokemonRemoteMediator {
        return PokemonRemoteMediator(pokemonRepository, remoteMediatorRepository)
    }

    private companion object {
        const val DB_NAME = "Pokemon.db"
    }
}