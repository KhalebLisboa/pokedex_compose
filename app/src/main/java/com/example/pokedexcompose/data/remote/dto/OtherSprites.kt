package com.example.pokedexcompose.data.remote.dto

import com.google.gson.annotations.SerializedName

data class OtherSprites(

    @SerializedName("dream_world")
    val dreamWorld: DreamWorld?,

    @SerializedName("home")
    val home: Home?,

    @SerializedName("official-artwork")
    val officialArtwork: OfficialArtwork?,

    @SerializedName("showdown")
    val showdown: Showdown?
)