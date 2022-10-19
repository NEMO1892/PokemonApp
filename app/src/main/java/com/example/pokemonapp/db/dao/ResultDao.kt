package com.example.pokemonapp.db.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.example.pokemonapp.model.Result

@Dao
interface ResultDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResults(quotes: ArrayList<Result>?)

    @Query("SELECT * FROM result")
    fun getAllResults(): PagingSource<Int, Result>

    @Query("DELETE FROM result")
    suspend fun clearAllResults()
}