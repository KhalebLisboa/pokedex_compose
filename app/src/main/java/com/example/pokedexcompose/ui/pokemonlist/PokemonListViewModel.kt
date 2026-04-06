package com.example.pokedexcompose.ui.pokemonlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.pokedexcompose.domain.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    private val _state = MutableStateFlow(PokemonListContract.State())
    val state = _state.asStateFlow()

    init {
        handleIntent(PokemonListContract.Intent.LoadPokemons)
    }

    fun handleIntent(intent: PokemonListContract.Intent) {
        when (intent) {
            is PokemonListContract.Intent.LoadPokemons -> loadPokemons()
        }
    }

    private fun loadPokemons() {
        val pokemonsFlow = repository.getPokemonList()
            .cachedIn(viewModelScope)
        
        _state.update { it.copy(pokemons = pokemonsFlow) }
    }
}
