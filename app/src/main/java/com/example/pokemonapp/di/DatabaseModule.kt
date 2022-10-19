package com.example.pokemonapp.di

import android.content.Context
import androidx.room.Room
import com.example.pokemonapp.db.AppDataBase
import com.example.pokemonapp.db.dao.ResultDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideDatabase(): AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            DB_NAME
        )
            .build()
    }

    @Provides
    fun provideLaunchesDao(database: AppDataBase): ResultDao = database.getResultDao()

    private companion object {
        const val DB_NAME = "Pokemon.db"
    }
}