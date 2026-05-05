package com.example.pokedexcompose.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PokemonDetailsDTO (
    @SerializedName("abilities")
    val abilities: List<AbilitySlot>,

    @SerializedName("base_experience")
    val baseExperience: Int,

    @SerializedName("forms")
    val forms: List<PokemonResultDto>,

    @SerializedName("game_indices")
    val gameIndices: List<GameIndex>,

    @SerializedName("height")
    val height: Int,

    @SerializedName("held_items")
    val heldItems: List<HeldItem>,

    @SerializedName("id")
    val id: Int,

    @SerializedName("is_default")
    val isDefault: Boolean,

    @SerializedName("location_area_encounters")
    val locationAreaEncounters: String,

    @SerializedName("moves")
    val moves: List<MoveSlot>,

    @SerializedName("name")
    val name: String,

    @SerializedName("order")
    val order: Int,

    @SerializedName("species")
    val species: PokemonResultDto,

    @SerializedName("sprites")
    val sprites: Sprites,

    @SerializedName("stats")
    val stats: List<StatSlot>,

    @SerializedName("types")
    val types: List<TypeSlot>,

    @SerializedName("weight")
    val weight: Int
)