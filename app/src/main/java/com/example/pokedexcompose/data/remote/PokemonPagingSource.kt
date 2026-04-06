package com.example.pokedexcompose.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.pokedexcompose.data.mapper.toDomain
import com.example.pokedexcompose.domain.model.Pokemon

class PokemonPagingSource(
    private val apiService: ApiService
) : PagingSource<Int, Pokemon>() {

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        val page = params.key ?: 0
        val pageSize = params.loadSize
        return try {
            val response = apiService.getPokemonList(
                limit = pageSize,
                offset = page * pageSize
            )
            val pokemonList = response.pokemons.map { it.toDomain() }
            
            LoadResult.Page(
                data = pokemonList,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (pokemonList.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
