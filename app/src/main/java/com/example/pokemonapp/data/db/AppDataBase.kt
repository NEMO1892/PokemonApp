package com.example.pokemonapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokemonapp.data.db.dao.RemoteKeysDao
import com.example.pokemonapp.data.db.dao.ResultDao
import com.example.pokemonapp.data.db.model.*
import com.example.pokemonapp.data.db.model.RemoteKeys

@Database(
    entities = [RemoteKeys::class, ResultRoomEntity::class],
    version = 2,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getResultDao(): ResultDao

    abstract fun remoteKeysDao(): RemoteKeysDao
}