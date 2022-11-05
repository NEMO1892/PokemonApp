package com.example.pokemonapp.data.network.model

import com.google.gson.annotations.SerializedName

data class PokemonResponseNetworkEntity(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: String,
    @SerializedName("results")
    val results: ArrayList<ResultNetworkEntity>
)