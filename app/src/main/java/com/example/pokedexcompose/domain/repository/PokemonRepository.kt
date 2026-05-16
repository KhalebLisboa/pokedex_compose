package com.example.pokedexcompose.domain.repository

import androidx.paging.PagingData
import com.example.pokedexcompose.core.Result
import com.example.pokedexcompose.data.remote.dto.PokemonDetailsDTO
import com.example.pokedexcompose.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemonList(): Flow<PagingData<Pokemon>>

    suspend fun getPokemonDetails(id: Int): PokemonDetailsDTO
}
