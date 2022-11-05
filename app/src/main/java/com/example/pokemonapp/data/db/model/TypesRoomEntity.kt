package com.example.pokemonapp.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.pokemonapp.data.db.converter.TypesTypeConverter

@Entity(tableName = "types")
data class TypesRoomEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long? = 0,
    @ColumnInfo(name = "slot")
    val slot: Int,
    @ColumnInfo(name = "type")
    @TypeConverters(TypesTypeConverter::class)
    val type: TypeRoomEntity
)
