package com.example.pokedexcompose.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TypeSlot(

    @SerializedName("slot")
    val slot: Int,

    @SerializedName("type")
    val type: PokemonResultDto
)