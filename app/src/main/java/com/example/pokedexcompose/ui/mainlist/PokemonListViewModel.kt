package com.example.pokedexcompose.ui.mainlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.pokedexcompose.domain.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    private val _state = MutableStateFlow(PokemonListContract.State())
    val state = _state.asStateFlow()

    private val _effect = Channel<PokemonListContract.Effect>()
    val effect = _effect.receiveAsFlow()


    init {
        handleIntent(PokemonListContract.Intent.LoadPokemons)
    }

    fun handleIntent(intent: PokemonListContract.Intent) {
        when (intent) {
            is PokemonListContract.Intent.LoadPokemons -> loadPokemons()
            is PokemonListContract.Intent.OnPokemonClicked -> {
                navigateToDetails(intent.id)
            }

        }
    }

    private fun loadPokemons() {
        val pokemonsFlow = repository.getPokemonList()
            .cachedIn(viewModelScope)
        
        _state.update { it.copy(pokemons = pokemonsFlow) }
    }

    private fun navigateToDetails(id: Int){
        viewModelScope.launch {
            _effect.send(PokemonListContract.Effect.NavigateToDetails(id))
        }
    }
}
