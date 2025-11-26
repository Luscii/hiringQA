package com.example.cookit.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookit.data.RecipeRepository
import com.example.cookit.model.Recipe
import java.util.UUID
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CookItUiState(
    val searchQuery: String = "",
    val recipes: List<Recipe> = RecipeRepository.allRecipes(),
    val ratings: Map<UUID, Int> = emptyMap()
)

class CookItViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CookItUiState())
    val uiState: StateFlow<CookItUiState> = _uiState.asStateFlow()

    fun onSearchChanged(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

    fun setRating(recipe: Recipe, rating: Int) {
        if (rating !in 1..5) return
        _uiState.update { state ->
            state.copy(ratings = state.ratings + (recipe.id to rating))
        }
    }

    fun ratingFor(recipe: Recipe): Int = _uiState.value.ratings[recipe.id] ?: 0

    fun filteredRecipes(): List<Recipe> = _uiState.value.let { state ->
        val query = state.searchQuery.trim()
        if (query.isEmpty()) {
            state.recipes
        } else {
            state.recipes.filter {
                it.title.contains(query, ignoreCase = true) ||
                    it.summary.contains(query, ignoreCase = true)
            }
        }
    }

    fun shouldCrash(recipe: Recipe): Boolean = RecipeRepository.shouldTriggerStabilityTest(recipe)
}
