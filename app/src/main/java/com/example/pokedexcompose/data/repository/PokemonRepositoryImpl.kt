package com.example.pokedexcompose.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.pokedexcompose.data.remote.ApiService
import com.example.pokedexcompose.data.remote.PokemonPagingSource
import com.example.pokedexcompose.domain.model.Pokemon
import com.example.pokedexcompose.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : PokemonRepository {
    override fun getPokemonList(): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(pageSize = 20 ),
            pagingSourceFactory = { PokemonPagingSource(apiService) }
        ).flow
    }
}
