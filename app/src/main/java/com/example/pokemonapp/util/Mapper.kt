package com.example.pokemonapp.util

import androidx.paging.PagingData
import androidx.paging.map
import com.example.pokemonapp.data.db.model.ResultRoomEntity
import com.example.pokemonapp.data.network.model.PokemonNetworkEntity
import com.example.pokemonapp.data.network.model.ResultNetworkEntity
import com.example.pokemonapp.data.network.model.TypeNetworkEntity
import com.example.pokemonapp.data.network.model.TypesNetworkEntity
import com.example.pokemonapp.domain.model.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun ArrayList<ResultNetworkEntity>?.mapResultFromNetworkEntityToRoomEntity(): ArrayList<ResultRoomEntity> =
    this?.map { resultNetworkEntity ->
        ResultRoomEntity(
            name = resultNetworkEntity.name,
            url = resultNetworkEntity.url
        )
    } as ArrayList<ResultRoomEntity>

fun Flow<PagingData<ResultRoomEntity>>.mapResultFromRoomEntityToDomainEntity(): Flow<PagingData<Result>> =
    this.map { pagingData ->
        pagingData.map { resultRoomEntity ->
            Result(
                resultRoomEntity.name,
                resultRoomEntity.url
            )
        }
    }

fun TypeNetworkEntity.fromNetworkEntityToDomainEntity(): Type =
    Type(
        name = name,
        url = url
    )

fun ArrayList<TypesNetworkEntity>.mapTypesNetworkEntityToDomainEntity(): ArrayList<Types> =
    map {
        Types(
            slot = it.slot,
            type = it.type.fromNetworkEntityToDomainEntity()
        )
    } as ArrayList<Types>

fun PokemonNetworkEntity.fromNetworkEntityToDomainEntity(): Pokemon =
    Pokemon(
        name = name,
        sprites = Sprites(sprites.front_default),
        types = types.mapTypesNetworkEntityToDomainEntity(),
        weight = weight,
        height = height
    )
