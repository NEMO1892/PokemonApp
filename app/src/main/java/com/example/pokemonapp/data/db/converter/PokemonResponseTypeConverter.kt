package com.example.pokemonapp.data.db.converter

import androidx.room.TypeConverter
import com.example.pokemonapp.data.db.model.ResultRoomEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PokemonResponseTypeConverter {
    @TypeConverter
    fun toResult(value: String?): ArrayList<ResultRoomEntity> {
        return Gson().fromJson(value, object : TypeToken<List<ResultRoomEntity>>() {}.type)
    }

    @TypeConverter
    fun fromResult(list: ArrayList<ResultRoomEntity?>?): String {
        return Gson().toJson(list)
    }
}