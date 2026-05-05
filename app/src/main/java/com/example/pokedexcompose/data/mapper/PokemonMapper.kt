package com.example.pokedexcompose.data.mapper

import com.example.pokedexcompose.data.remote.dto.PokemonResultDto
import com.example.pokedexcompose.domain.model.Pokemon

fun PokemonResultDto.toDomain(): Pokemon {
    val id = url.split("/").last { it.isNotEmpty() }
    val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${id}.png"
    return Pokemon(
        id = id.toInt(),
        name = name.replaceFirstChar { it.uppercase() },
        url = url,
        imageUrl = imageUrl
    )
}
