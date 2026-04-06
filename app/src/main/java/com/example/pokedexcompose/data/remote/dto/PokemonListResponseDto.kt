package com.example.pokedexcompose.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PokemonListResponseDto(
    @SerializedName("count")
    val totalCount: Int,
    @SerializedName("next")
    val nextPage: String?,
    @SerializedName("previous")
    val previousPage: String?,
    @SerializedName("results")
    val pokemons: List<PokemonResultDto>
)
