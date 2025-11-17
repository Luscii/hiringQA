package com.example.cookit.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cookit.ui.screens.RecipeDetailScreen
import com.example.cookit.ui.screens.RecipeListScreen
import com.example.cookit.ui.theme.CookItTheme
import java.util.UUID

const val LIST_ROUTE = "recipes"
const val DETAIL_ROUTE = "recipes/detail/{recipeId}"

@Composable
fun CookItApp(cookItViewModel: CookItViewModel = viewModel()) {
    CookItTheme {
        val uiState by cookItViewModel.uiState.collectAsState()
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = LIST_ROUTE) {
            composable(LIST_ROUTE) {
                RecipeListScreen(
                    recipes = cookItViewModel.filteredRecipes(),
                    searchQuery = uiState.searchQuery,
                    onSearchChanged = cookItViewModel::onSearchChanged,
                    onRecipeSelected = { recipe ->
                        navController.navigate("recipes/detail/${recipe.id}")
                    }
                )
            }
            composable(
                route = DETAIL_ROUTE,
                arguments = listOf(navArgument("recipeId") { type = NavType.StringType })
            ) { entry ->
                val recipeId = entry.arguments?.getString("recipeId")?.let(UUID::fromString)
                val recipe = uiState.recipes.firstOrNull { it.id == recipeId }
                requireNotNull(recipe) { "Recipe not found" }
                RecipeDetailScreen(
                    recipe = recipe,
                    rating = cookItViewModel.ratingFor(recipe),
                    onRate = { cookItViewModel.setRating(recipe, it) },
                    shouldCrash = cookItViewModel.shouldCrash(recipe),
                    onNavigateBack = { navController.navigateUp() }
                )
            }
        }
    }
}
