package com.example.pokemonapp.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pokemonapp.model.Result
import javax.inject.Inject

//class PokemonDataSource @Inject constructor(
//    private val networkRepository: NetworkRepository
//) : PagingSource<Int, Result>() {
//
//    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
//        return state.anchorPosition?.let { anchorPosition ->
//            val anchorPage = state.closestPageToPosition(anchorPosition)
//            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
//        }
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
////        val key = params.key ?: 0
//        val pageNumber = params.key ?: 0
//
//
//        return try {
////            val nextKey = key + 20
//            val response = networkRepository.getListPokemons(offset = (pageNumber * 20))
//            val nextPageNumber = if (response.body()?.results!!.isEmpty()) null else pageNumber + 1
//            val prevPageNumber = if (pageNumber > 1) pageNumber - 1 else null
//            LoadResult.Page(
//                data = response.body()?.results as ArrayList<Result>,
//                prevKey = prevPageNumber,
//                nextKey = nextPageNumber
//            )
//        } catch (e: Exception) {
//            LoadResult.Error(e)
//        }
//    }
//}