package com.example.pokedexcompose.ui.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun PokemonDetailsScreen(
    viewModel: PokemonDetailsViewModel,
    id: Int
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.handleIntent(PokemonDetailsContract.Intent.LoadPokemonDetails(id))
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        when(state){
            is PokemonDetailsContract.State.Loading -> {
                Text("Loading")
            }
            is PokemonDetailsContract.State.Error -> {Text("Deu Erro !")}
            is PokemonDetailsContract.State.Success -> {
                val pokemonDetails = (state as PokemonDetailsContract.State.Success).pokemonDetails
                val artWork = pokemonDetails.sprites.other?.officialArtwork?.frontDefault ?: pokemonDetails.sprites.frontDefault

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    AsyncImage(
                        model = artWork,
                        contentDescription = null,
                        modifier = Modifier.size(100.dp)
                    )
                    Spacer(Modifier.height(10.dp))
                    Text(pokemonDetails.name)
                }
            }
        }
    }
}