package com.example.pokemonapp.data.network.model

import com.google.gson.annotations.SerializedName

data class TypesNetworkEntity (
    @SerializedName("slot")
    val slot: Int,
    @SerializedName("type")
    val type: TypeNetworkEntity
)