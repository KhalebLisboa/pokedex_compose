package com.example.pokedexcompose.data.remote.dto

import com.google.gson.annotations.SerializedName

data class StatSlot(

    @SerializedName("base_stat")
    val baseStat: Int,

    @SerializedName("effort")
    val effort: Int,

    @SerializedName("stat")
    val stat: PokemonResultDto
)