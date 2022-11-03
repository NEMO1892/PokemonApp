package com.example.pokemonapp.data.db.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.example.pokemonapp.data.db.model.ResultRoomEntity

@Dao
interface ResultDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResults(quotes: ArrayList<ResultRoomEntity>?)

    @Query("SELECT * FROM resultRoom")
    fun getAllResults(): PagingSource<Int, ResultRoomEntity>

    @Query("DELETE FROM resultRoom")
    suspend fun clearAllResults()
}