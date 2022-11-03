package com.example.pokemonapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.pokemonapp.data.db.AppDataBase
import com.example.pokemonapp.data.db.dao.ResultDao
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
    fun provideResultDao(database: AppDataBase): ResultDao = database.getResultDao()

    private companion object {
        const val DB_NAME = "Pokemon.db"
    }
}