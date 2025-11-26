package com.example.cookit.model

import java.util.UUID
import kotlin.math.min
import java.text.DecimalFormat

data class Ingredient(
    val name: String,
    val unit: String? = null,
    val quantity: Double? = null
) {
    fun description(servings: Int, baseServings: Int): String {
        val safeBase = baseServings.takeIf { it > 0 } ?: 1
        val effectiveServings = min(servings, 10)
        val factor = quantity?.let { (effectiveServings.toDouble() / safeBase) * it }
        val formattedAmount = factor?.let {
            val formatter = DecimalFormat("#.##")
            formatter.format(it)
        }
        val amountPart = when {
            formattedAmount != null && unit != null -> "$formattedAmount $unit "
            formattedAmount != null -> "$formattedAmount "
            else -> ""
        }
        return (amountPart + name).trim()
    }
}

data class Recipe(
    val id: UUID,
    val title: String,
    val summary: String,
    val durationMinutes: Int,
    val difficulty: Difficulty,
    val imageName: String?,
    val baseServings: Int,
    val ingredients: List<Ingredient>,
    val steps: List<String>
)

enum class Difficulty { EASY, MEDIUM, HARD }
