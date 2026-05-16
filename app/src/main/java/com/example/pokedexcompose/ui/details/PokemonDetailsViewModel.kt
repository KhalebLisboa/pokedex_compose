package com.example.pokedexcompose.ui.details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexcompose.domain.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class PokemonDetailsViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    private val _state = MutableStateFlow<PokemonDetailsContract.State>(PokemonDetailsContract.State.Loading)
    val state = _state.asStateFlow()

    fun handleIntent(intent: PokemonDetailsContract.Intent) {
        when (intent) {
            is PokemonDetailsContract.Intent.LoadPokemonDetails -> getPokemonDetails(intent.id)
        }
    }

    fun getPokemonDetails(id: Int) {
        viewModelScope.launch {
            try {
                _state.value = PokemonDetailsContract.State.Loading
                val pokemonDetails = repository.getPokemonDetails(id)
                _state.value = PokemonDetailsContract.State.Success(pokemonDetails)
            } catch (e: Exception) {
                Log.e(TAG, e.message.toString())
                _state.value = PokemonDetailsContract.State.Error
            }
        }
    }

    companion object{
        val TAG: String = this::class.java.simpleName

    }
}