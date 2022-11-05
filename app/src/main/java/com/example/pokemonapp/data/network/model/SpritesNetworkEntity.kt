package com.example.pokemonapp.data.network.model

import com.google.gson.annotations.SerializedName

data class SpritesNetworkEntity(
    @SerializedName("front_default")
    val front_default: String
)
