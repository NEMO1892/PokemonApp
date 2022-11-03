package com.example.pokemonapp.data.db.converter

import androidx.room.TypeConverter
import com.example.pokemonapp.data.db.model.TypeRoomEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TypesTypeConverter {

    @TypeConverter
    fun toType(value: String?): TypeRoomEntity {
        return Gson().fromJson(value, object : TypeToken<TypeRoomEntity>() {}.type)
    }

    @TypeConverter
    fun fromType(sprites: TypeRoomEntity): String {
        return Gson().toJson(sprites)
    }
}