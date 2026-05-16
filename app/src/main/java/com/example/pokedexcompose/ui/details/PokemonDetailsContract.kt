package com.example.pokedexcompose.ui.details

import com.example.pokedexcompose.data.remote.dto.PokemonDetailsDTO

class PokemonDetailsContract {
    sealed class State {
        object Loading : State()
        object Error : State()
        data class Success(val pokemonDetails: PokemonDetailsDTO) : State()
    }

    sealed class Intent {
        data class LoadPokemonDetails(val id: Int) : Intent()
    }
}