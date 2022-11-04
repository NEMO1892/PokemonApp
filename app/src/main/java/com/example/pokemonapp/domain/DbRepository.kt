package com.example.pokemonapp.domain

import androidx.paging.PagingData
import com.example.pokemonapp.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface DbRepository {

    fun getPokemons(): Flow<PagingData<Result>>
}