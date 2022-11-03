package com.example.pokemonapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pokemonapp.data.db.converter.PokemonResponseTypeConverter
import com.example.pokemonapp.data.db.converter.PokemonTypeConverter
import com.example.pokemonapp.data.db.converter.TypesTypeConverter
import com.example.pokemonapp.data.db.dao.RemoteKeysDao
import com.example.pokemonapp.data.db.dao.ResultDao
import com.example.pokemonapp.data.db.model.*
import com.example.pokemonapp.data.db.model.RemoteKeys
import com.example.pokemonapp.domain.model.Result

@Database(
    entities = [RemoteKeys::class, PokemonResponseRoomEntity::class,
        PokemonRoomEntity::class, SpritesRoomEntity::class, TypesRoomEntity::class,
        TypeRoomEntity::class, ResultRoomEntity::class, Result::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(
    PokemonResponseTypeConverter::class,
    PokemonTypeConverter::class,
    TypesTypeConverter::class
)
abstract class AppDataBase : RoomDatabase() {

    abstract fun getResultDao(): ResultDao

    abstract fun remoteKeysDao(): RemoteKeysDao
}