package com.example.pokedexcompose.data.remote

import com.example.pokedexcompose.data.remote.dto.PokemonListResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonListResponseDto
}
