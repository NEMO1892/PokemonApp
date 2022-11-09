package com.example.pokemonapp.data.repository

import androidx.paging.PagingSource
import com.example.pokemonapp.data.db.dao.RemoteKeysDao
import com.example.pokemonapp.data.db.dao.ResultDao
import com.example.pokemonapp.data.db.model.RemoteKeys
import com.example.pokemonapp.data.db.model.ResultRoomEntity
import com.example.pokemonapp.data.network.PokemonApi
import com.example.pokemonapp.data.network.model.PokemonNetworkEntity
import com.example.pokemonapp.data.network.model.PokemonResponseNetworkEntity
import com.example.pokemonapp.domain.PokemonRepository
import retrofit2.Response
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val api: PokemonApi,
    private val resultDao: ResultDao,
    private val remoteKeysDao: RemoteKeysDao
) : PokemonRepository {

    override suspend fun getListPokemons(
        offset: Int?,
        limit: Int?
    ): Response<PokemonResponseNetworkEntity> =
        api.getPokemons(offset = offset, limit = limit)

    override suspend fun getOnePokemon(id: Int?): Response<PokemonNetworkEntity> =
        api.getOnePokemon(id = id)

    override suspend fun insertResults(results: ArrayList<ResultRoomEntity>?) {
        resultDao.insertResults(results)
    }

    override fun getAllResults(): PagingSource<Int, ResultRoomEntity> = resultDao.getAllResults()

    override suspend fun clearAllResults() {
        resultDao.clearAllResults()
    }

    override suspend fun getRemoteKey(nameId: String): RemoteKeys =
        remoteKeysDao.getRemoteKey(nameId)

    override suspend fun addAllRemoteKeys(remoteKeys: List<RemoteKeys>?) {
        remoteKeysDao.addAllRemoteKeys(remoteKeys)
    }

    override suspend fun deleteAllRemoteKeys() {
        remoteKeysDao.deleteAllRemoteKeys()
    }
}