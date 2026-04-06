package com.example.pokedexcompose.ui.pokemonlist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.pokedexcompose.domain.model.Pokemon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonListScreen(
    viewModel: PokemonListViewModel
) {
    val state by viewModel.state.collectAsState()
    val lazyPagingItems = state.pokemons.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Pokedex") }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(lazyPagingItems.itemCount) { index ->
                    val pokemon = lazyPagingItems[index]
                    if (pokemon != null) {
                        PokemonItem(pokemon = pokemon)
                    }
                }

                when (val loadState = lazyPagingItems.loadState.append) {
                    is LoadState.Loading -> {
                        item {
                            LoadingIndicator()
                        }
                    }
                    is LoadState.Error -> {
                        item {
                            ErrorRetry(message = loadState.error.message ?: "Error") {
                                lazyPagingItems.retry()
                            }
                        }
                    }
                    else -> {}
                }
            }

            if (lazyPagingItems.loadState.refresh is LoadState.Loading) {
                LoadingIndicator(modifier = Modifier.align(Alignment.Center))
            }

            if (lazyPagingItems.loadState.refresh is LoadState.Error) {
                val error = lazyPagingItems.loadState.refresh as LoadState.Error
                ErrorRetry(
                    message = error.error.message ?: "Error",
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    lazyPagingItems.retry()
                }
            }
        }
    }
}

@Composable
fun PokemonItem(pokemon: Pokemon) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = pokemon.imageUrl,
                contentDescription = pokemon.name,
                modifier = Modifier.size(60.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = pokemon.name,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
fun LoadingIndicator(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorRetry(
    message: String,
    modifier: Modifier = Modifier,
    onRetry: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = message, color = MaterialTheme.colorScheme.error)
        Button(onClick = onRetry) {
            Text("Retry")
        }
    }
}
