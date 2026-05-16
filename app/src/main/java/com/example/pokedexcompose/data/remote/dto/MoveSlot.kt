package com.example.pokedexcompose.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MoveSlot(

    @SerializedName("move")
    val move: PokemonResultDto,

    @SerializedName("version_group_details")
    val versionGroupDetails: List<MoveVersionDetail>
)

data class MoveVersionDetail(

    @SerializedName("level_learned_at")
    val levelLearnedAt: Int,

    @SerializedName("move_learn_method")
    val moveLearnMethod: PokemonResultDto,

    @SerializedName("version_group")
    val versionGroup: PokemonResultDto
)