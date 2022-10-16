package com.example.pokemonapp.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pokemonapp.model.Result
import javax.inject.Inject

class PokemonDataSource @Inject constructor(
    private val networkRepository: NetworkRepository
) : PagingSource<Int, Result>() {

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val key = params.key ?: 0
            val nextKey = key + 20
            val response = networkRepository.getListPokemons(offset = key)
            LoadResult.Page(
                data = response.body()?.results as ArrayList<Result>,
                prevKey = null,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}