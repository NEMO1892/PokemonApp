package com.example.pokemonapp.data.db.converter

import androidx.room.TypeConverter
import com.example.pokemonapp.data.db.model.SpritesRoomEntity
import com.example.pokemonapp.data.db.model.TypesRoomEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PokemonTypeConverter {

    @TypeConverter
    fun toSprites(value: String?): SpritesRoomEntity {
        return Gson().fromJson(value, object : TypeToken<SpritesRoomEntity>() {}.type)
    }

    @TypeConverter
    fun fromSprites(sprites: SpritesRoomEntity): String {
        return Gson().toJson(sprites)
    }

    @TypeConverter
    fun toTypes(value: String?): ArrayList<TypesRoomEntity> {
        return Gson().fromJson(value, object : TypeToken<List<TypesRoomEntity>>() {}.type)
    }

    @TypeConverter
    fun fromTypes(list: ArrayList<TypesRoomEntity?>?): String {
        return Gson().toJson(list)
    }
}