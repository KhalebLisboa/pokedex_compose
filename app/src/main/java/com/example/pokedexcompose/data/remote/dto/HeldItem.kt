package com.example.pokedexcompose.data.remote.dto

import com.google.gson.annotations.SerializedName

data class HeldItem(

    @SerializedName("item")
    val item: PokemonResultDto,

    @SerializedName("version_details")
    val versionDetails: List<VersionDetail>
)

data class VersionDetail(

    @SerializedName("rarity")
    val rarity: Int,

    @SerializedName("version")
    val version: PokemonResultDto
)