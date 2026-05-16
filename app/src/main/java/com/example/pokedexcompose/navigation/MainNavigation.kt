package com.example.pokedexcompose.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pokedexcompose.ui.details.PokemonDetailsScreen
import com.example.pokedexcompose.ui.mainlist.PokemonListScreen

@Composable
fun MainNavigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "home"){
        composable("home"){
            PokemonListScreen(viewModel = hiltViewModel(), navController)
        }

        composable("details/{id}", arguments = listOf(navArgument("id") { type = NavType.IntType })){
            val id = it.arguments?.getInt("id") ?: run {
                navController.popBackStack()
                return@composable
            }

            PokemonDetailsScreen(viewModel = hiltViewModel(), id = id)
        }
    }
}