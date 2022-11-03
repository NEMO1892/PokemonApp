package com.example.pokemonapp.data.network.model

import com.example.pokemonapp.domain.model.Sprites
import com.google.gson.annotations.SerializedName

data class PokemonNetworkEntity(
    @SerializedName("name")
    val name: String,
    @SerializedName("sprites")
    val sprites: Sprites,
    @SerializedName("types")
    val types: ArrayList<TypesNetworkEntity>,
    @SerializedName("weight")
    val weight: Double,
    @SerializedName("height")
    val height: Double
)
