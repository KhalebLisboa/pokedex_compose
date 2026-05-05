package com.example.pokedexcompose.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AbilitySlot(

    @SerializedName("ability")
    val ability: PokemonResultDto,

    @SerializedName("is_hidden")
    val isHidden: Boolean,

    @SerializedName("slot")
    val slot: Int
)