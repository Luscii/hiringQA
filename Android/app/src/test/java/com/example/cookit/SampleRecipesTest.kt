package com.example.cookit

import com.example.cookit.data.RecipeRepository
import org.junit.Assert.assertEquals
import org.junit.Test

class SampleRecipesTest {
    @Test
    fun `repository exposes expected sample count`() {
        assertEquals(13, RecipeRepository.allRecipes().size)
    }
}
