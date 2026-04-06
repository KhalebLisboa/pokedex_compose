package com.example.pokedexcompose.ui.pokemonlist

import androidx.paging.PagingData
import com.example.pokedexcompose.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class PokemonListContract {
    data class State(
        val pokemons: Flow<PagingData<Pokemon>> = emptyFlow()
    )

    sealed class Intent {
        object LoadPokemons : Intent()
    }

    sealed class Effect {
        data class ShowError(val message: String) : Effect()
    }
}
