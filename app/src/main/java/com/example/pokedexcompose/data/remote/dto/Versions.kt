package com.example.pokedexcompose.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Versions(
    @SerializedName("generation-i")
    val generationI: GenerationI?,

    @SerializedName("generation-ii")
    val generationII: GenerationII?
)

data class GenerationI(
    @SerializedName("red-blue")
    val redBlue: RedBlue?,

    @SerializedName("yellow")
    val yellow: RedBlue?
)

data class GenerationII(
    @SerializedName("crystal")
    val crystal: Crystal?,

    @SerializedName("gold")
    val gold: Gold?,

    @SerializedName("silver")
    val silver: Gold?
)

data class RedBlue(
    @SerializedName("front_default")
    val frontDefault: String?,

    @SerializedName("back_default")
    val backDefault: String?
)

data class Gold(
    @SerializedName("front_default")
    val frontDefault: String?,

    @SerializedName("back_default")
    val backDefault: String?
)

data class Crystal(
    @SerializedName("front_default")
    val frontDefault: String?,

    @SerializedName("back_default")
    val backDefault: String?,

    @SerializedName("front_shiny")
    val frontShiny: String?,

    @SerializedName("back_shiny")
    val backShiny: String?
)