package com.example.cookit.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.cookit.model.Recipe
import kotlin.math.min

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetailScreen(
    recipe: Recipe,
    rating: Int,
    onRate: (Int) -> Unit,
    shouldCrash: Boolean,
    onNavigateBack: () -> Unit
) {
    if (shouldCrash) {
        error("Simulated data corruption: Unable to load this recipe's steps. Please report to QA.")
    }

    var showRatingDialog by remember { mutableStateOf(false) }
    var selectedServings by remember { mutableStateOf(maxOf(recipe.baseServings, 1)) }
    val effectiveServings = min(selectedServings, 10)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(recipe.title, maxLines = 1) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { showRatingDialog = true }) {
                        Icon(Icons.Filled.Star, contentDescription = "Rate")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { RecipeHero(imageName = recipe.imageName, title = recipe.title) }
            item {
                SummarySection(recipe = recipe)
            }
            item {
                ServingsSection(
                    servings = selectedServings,
                    onServingsChange = { selectedServings = it },
                    capped = selectedServings > 10
                )
            }
            if (rating > 0) {
                item {
                    Surface(shape = MaterialTheme.shapes.medium, tonalElevation = 2.dp) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Rating", style = MaterialTheme.typography.titleMedium)
                            Text("$rating / 5", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
            item {
                Text("Ingredients", style = MaterialTheme.typography.titleLarge)
            }
            itemsIndexed(recipe.ingredients) { _, ingredient ->
                Text(
                    text = "• " + ingredient.description(effectiveServings, recipe.baseServings),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            item {
                Text("Steps", style = MaterialTheme.typography.titleLarge)
            }
            itemsIndexed(recipe.steps) { index, step ->
                Text(
                    text = "${index + 1}. $step",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            item {
                Button(onClick = { showRatingDialog = true }, modifier = Modifier.fillMaxWidth()) {
                    Text("Leave Feedback")
                }
            }
        }
    }

    if (showRatingDialog) {
        RatingDialog(
            currentRating = rating,
            onDismiss = { showRatingDialog = false },
            onConfirm = { value ->
                onRate(value)
                showRatingDialog = false
            }
        )
    }
}

@Composable
private fun RecipeHero(imageName: String?, title: String) {
    val context = LocalContext.current
    val drawableId = imageName?.let {
        context.resources.getIdentifier(it, "drawable", context.packageName)
    } ?: 0

    if (drawableId != 0) {
        Surface(shape = MaterialTheme.shapes.large, tonalElevation = 2.dp) {
            androidx.compose.foundation.Image(
                painter = painterResource(id = drawableId),
                contentDescription = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            )
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(MaterialTheme.colorScheme.surfaceVariant, shape = MaterialTheme.shapes.large)
        )
    }
}

@Composable
private fun SummarySection(recipe: Recipe) {
    Surface(shape = MaterialTheme.shapes.medium, tonalElevation = 1.dp) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = recipe.summary, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "${recipe.durationMinutes} min • ${recipe.difficulty}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
private fun ServingsSection(servings: Int, onServingsChange: (Int) -> Unit, capped: Boolean) {
    var expanded by remember { mutableStateOf(false) }
    Column {
        Text("Servings", style = MaterialTheme.typography.titleMedium)
        Box {
            androidx.compose.material3.OutlinedButton(onClick = { expanded = true }) {
                Text("$servings people")
            }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                (1..20).forEach { value ->
                    DropdownMenuItem(
                        text = { Text("$value") },
                        onClick = {
                            onServingsChange(value)
                            expanded = false
                        }
                    )
                }
            }
        }
        if (capped) {
            Text(
                text = "Amounts shown for 10 people (maximum scaling).",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Composable
private fun RatingDialog(
    currentRating: Int,
    onDismiss: () -> Unit,
    onConfirm: (Int) -> Unit
) {
    var selectedRating by remember { mutableStateOf(currentRating.takeIf { it in 1..5 } ?: 0) }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("How was the recipe?") },
        text = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                (1..5).forEach { value ->
                    IconButton(onClick = { selectedRating = value }) {
                        Icon(
                            imageVector = if (value <= selectedRating) Icons.Filled.Star else Icons.Outlined.Star,
                            contentDescription = "$value stars",
                            tint = if (value <= selectedRating) MaterialTheme.colorScheme.primary else Color.Gray
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = { if (selectedRating in 1..5) onConfirm(selectedRating) }) {
                Text("Submit")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}
