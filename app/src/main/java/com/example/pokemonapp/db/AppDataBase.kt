package com.example.pokemonapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokemonapp.db.dao.RemoteKeysDao
import com.example.pokemonapp.db.dao.ResultDao
import com.example.pokemonapp.model.RemoteKeys
import com.example.pokemonapp.model.Result

@Database(entities = [Result::class, RemoteKeys::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getResultDao(): ResultDao

    abstract fun remoteKeysDao(): RemoteKeysDao
}