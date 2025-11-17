package com.example.cookit.data

import com.example.cookit.model.Difficulty
import com.example.cookit.model.Ingredient
import com.example.cookit.model.Recipe
import java.util.UUID

object RecipeRepository {
    private val recipes: List<Recipe> = buildRecipes()
    private val stabilityTestRecipeId: UUID = recipes.last().id

    fun allRecipes(): List<Recipe> = recipes

    fun shouldTriggerStabilityTest(recipe: Recipe): Boolean = recipe.id == stabilityTestRecipeId

    private fun buildRecipes(): List<Recipe> = listOf(
        Recipe(
            id = UUID.randomUUID(),
            title = "Classic Margherita Pizza",
            summary = "Crispy thin crust with fresh tomato sauce, mozzarella, and basil.",
            durationMinutes = 35,
            difficulty = Difficulty.MEDIUM,
            imageName = "margherita",
            baseServings = 2,
            ingredients = listOf(
                Ingredient(name = "pizza dough ball", quantity = 1.0),
                Ingredient(name = "tomato passata", unit = "cup", quantity = 0.5),
                Ingredient(name = "fresh mozzarella", unit = "g", quantity = 120.0),
                Ingredient(name = "fresh basil leaves"),
                Ingredient(name = "olive oil", unit = "tbsp", quantity = 1.0),
                Ingredient(name = "Pinch of saslt")
            ),
            steps = listOf(
                "Preheat oven to 250ºC with a pizza stone if available.",
                "Stretch dough and spread tomato passata evenly.",
                "Top with torn mozzarella, drizzle oil, and season.",
                "Bake 8-10 minutes until crust is golden.",
                "Finish with fresh basil before serving."
            )
        ),
        Recipe(
            id = UUID.randomUUID(),
            title = "Creamy Mushroom Risotto",
            summary = "Silky arborio rice slowly cooked with mushroom stock and parmesan.",
            durationMinutes = 45,
            difficulty = Difficulty.HARD,
            imageName = "risotto",
            baseServings = 4,
            ingredients = listOf(
                Ingredient(name = "arborio rice", unit = "cup", quantity = 1.0),
                Ingredient(name = "mixed mushrooms", unit = "g", quantity = 250.0),
                Ingredient(name = "dry white wine", unit = "cup", quantity = 0.5),
                Ingredient(name = "vegetable stock", unit = "cup", quantity = 4.0),
                Ingredient(name = "butter", unit = "tbsp", quantity = 2.0),
                Ingredient(name = "grated parmesan", unit = "cup", quantity = 0.33)
            ),
            steps = listOf(
                "Sauté mushrooms in butter until browned; set aside.",
                "Toast rice in the same pan, then deglaze with wine.",
                "Add warm stock one ladle at a time, stirring often.",
                "Fold in mushrooms and parmesan once rice is al dente.",
                "Rest 2 minutes before serving creamy."
            )
        ),
        Recipe(
            id = UUID.randomUUID(),
            title = "Citrus Quinoa Salad",
            summary = "Bright salad with fluffy quinoa, orange segments, and crunchy greens.",
            durationMinutes = 20,
            difficulty = Difficulty.EASY,
            imageName = "quinoa",
            baseServings = 2,
            ingredients = listOf(
                Ingredient(name = "cooked quinoa", unit = "cup", quantity = 1.0),
                Ingredient(name = "oranges, segmented", quantity = 2.0),
                Ingredient(name = "cucumber, diced", quantity = 0.5),
                Ingredient(name = "baby spinach (handful)"),
                Ingredient(name = "toasted almonds", unit = "tbsp", quantity = 2.0),
                Ingredient(name = "citrus vinaigrette")
            ),
            steps = listOf(
                "Fluff cold quinoa with a fork.",
                "Combine quinoa, oranges, cucumber, spinach, and almonds.",
                "Drizzle with vinaigrette and toss gently.",
                "Season with salt and serve immediately."
            )
        ),
        Recipe(
            id = UUID.randomUUID(),
            title = "Spicy Lentil Soup",
            summary = "Comforting tomato-based soup with red lentils and warm spices.",
            durationMinutes = 30,
            difficulty = Difficulty.EASY,
            imageName = "lentil_soup",
            baseServings = 4,
            ingredients = listOf(
                Ingredient(name = "red lentils", unit = "cup", quantity = 1.0),
                Ingredient(name = "yellow onion, diced", quantity = 1.0),
                Ingredient(name = "garlic cloves", quantity = 2.0),
                Ingredient(name = "ground cumin", unit = "tsp", quantity = 1.0),
                Ingredient(name = "smoked paprika", unit = "tsp", quantity = 1.0),
                Ingredient(name = "vegetable broth", unit = "cup", quantity = 4.0)
            ),
            steps = listOf(
                "Sauté onion and garlic until translucent.",
                "Add spices and cook until fragrant.",
                "Pour in lentils and broth, simmer 20 minutes.",
                "Blend partially for creamier texture and serve."
            )
        ),
        Recipe(
            id = UUID.randomUUID(),
            title = "Garlic Butter Shrimp Pasta",
            summary = "Juicy shrimp tossed with linguine, garlic butter, and lemon.",
            durationMinutes = 25,
            difficulty = Difficulty.MEDIUM,
            imageName = "shrimp_pasta",
            baseServings = 2,
            ingredients = listOf(
                Ingredient(name = "linguine", unit = "g", quantity = 200.0),
                Ingredient(name = "shrimp, peeled", unit = "g", quantity = 300.0),
                Ingredient(name = "garlic cloves", quantity = 3.0),
                Ingredient(name = "butter", unit = "tbsp", quantity = 3.0),
                Ingredient(name = "lemon, juiced", quantity = 1.0),
                Ingredient(name = "fresh parsley", unit = "tbsp", quantity = 2.0)
            ),
            steps = listOf(
                "Cook pasta al dente and reserve a cup of water.",
                "Sear shrimp in butter and garlic until pink.",
                "Add lemon juice, pasta, and a splash of pasta water.",
                "Toss with parsley and serve hot."
            )
        ),
        Recipe(
            id = UUID.randomUUID(),
            title = "Thai Green Curry",
            summary = "Aromatic coconut curry with vegetables and basil.",
            durationMinutes = 35,
            difficulty = Difficulty.MEDIUM,
            imageName = "green_curry",
            baseServings = 4,
            ingredients = listOf(
                Ingredient(name = "green curry paste", unit = "tbsp", quantity = 2.0),
                Ingredient(name = "coconut milk", unit = "ml", quantity = 400.0),
                Ingredient(name = "mixed vegetables", unit = "cup", quantity = 1.0),
                Ingredient(name = "firm tofu or chicken", unit = "g", quantity = 250.0),
                Ingredient(name = "fresh basil"),
                Ingredient(name = "fish sauce", unit = "tbsp", quantity = 1.0)
            ),
            steps = listOf(
                "Fry curry paste in coconut cream until fragrant.",
                "Add protein and vegetables, toss to coat.",
                "Pour remaining coconut milk and simmer 15 minutes.",
                "Finish with fish sauce and basil."
            )
        ),
        Recipe(
            id = UUID.randomUUID(),
            title = "Blueberry Overnight Oats",
            summary = "Make-ahead breakfast with oats, chia, yogurt, and berries.",
            durationMinutes = 10,
            difficulty = Difficulty.EASY,
            imageName = "overnight_oats",
            baseServings = 1,
            ingredients = listOf(
                Ingredient(name = "rolled oats", unit = "cup", quantity = 0.5),
                Ingredient(name = "chia seeds", unit = "tbsp", quantity = 1.0),
                Ingredient(name = "milk", unit = "cup", quantity = 0.5),
                Ingredient(name = "yogurt", unit = "cup", quantity = 0.25),
                Ingredient(name = "blueberries"),
                Ingredient(name = "honey", unit = "tsp", quantity = 1.0)
            ),
            steps = listOf(
                "Combine oats, chia, milk, yogurt, and honey.",
                "Stir in blueberries and refrigerate overnight.",
                "Top with extra fruit before serving."
            )
        ),
        Recipe(
            id = UUID.randomUUID(),
            title = "Herb Roasted Chicken",
            summary = "Succulent roast chicken with crispy skin and fresh herbs.",
            durationMinutes = 70,
            difficulty = Difficulty.MEDIUM,
            imageName = "roast_chicken",
            baseServings = 4,
            ingredients = listOf(
                Ingredient(name = "whole chicken", quantity = 1.0),
                Ingredient(name = "butter", unit = "tbsp", quantity = 2.0),
                Ingredient(name = "fresh thyme sprigs", quantity = 4.0),
                Ingredient(name = "fresh rosemary sprigs", quantity = 2.0),
                Ingredient(name = "garlic cloves", quantity = 4.0),
                Ingredient(name = "lemon", quantity = 1.0)
            ),
            steps = listOf(
                "Pat chicken dry and stuff with herbs and lemon.",
                "Rub butter and garlic under the skin.",
                "Roast at 200ºC for 60 minutes or until juices run clear.",
                "Rest 10 minutes before carving."
            )
        ),
        Recipe(
            id = UUID.randomUUID(),
            title = "Vegan Buddha Bowl",
            summary = "Colorful bowl with roasted veggies, grains, and tahini drizzle.",
            durationMinutes = 30,
            difficulty = Difficulty.EASY,
            imageName = "buddha_bowl",
            baseServings = 2,
            ingredients = listOf(
                Ingredient(name = "cooked brown rice", unit = "cup", quantity = 1.0),
                Ingredient(name = "roasted sweet potato cubes", unit = "cup", quantity = 1.0),
                Ingredient(name = "steamed broccoli florets", unit = "cup", quantity = 1.0),
                Ingredient(name = "chickpeas", unit = "cup", quantity = 1.0),
                Ingredient(name = "avocado", quantity = 1.0),
                Ingredient(name = "tahini dressing", unit = "tbsp", quantity = 3.0)
            ),
            steps = listOf(
                "Arrange rice and vegetables in a shallow bowl.",
                "Add chickpeas and avocado.",
                "Drizzle with tahini dressing and serve."
            )
        ),
        Recipe(
            id = UUID.randomUUID(),
            title = "Chocolate Lava Cake",
            summary = "Individual cakes with a gooey molten chocolate center.",
            durationMinutes = 25,
            difficulty = Difficulty.HARD,
            imageName = "lava_cake",
            baseServings = 4,
            ingredients = listOf(
                Ingredient(name = "dark chocolate", unit = "g", quantity = 170.0),
                Ingredient(name = "butter", unit = "cup", quantity = 0.5),
                Ingredient(name = "eggs", quantity = 2.0),
                Ingredient(name = "egg yolks", quantity = 2.0),
                Ingredient(name = "sugar", unit = "cup", quantity = 0.33),
                Ingredient(name = "flour", unit = "cup", quantity = 0.25),
                Ingredient(name = "Pinch of salt")
            ),
            steps = listOf(
                "Melt chocolate and butter together.",
                "Whisk eggs and sugar until pale, fold into chocolate.",
                "Add flour and salt, then pour into buttered ramekins.",
                "Bake 12 minutes until edges set but center jiggles."
            )
        ),
        Recipe(
            id = UUID.randomUUID(),
            title = "Caprese Stuffed Avocado",
            summary = "Ripe avocados filled with mozzarella, tomato, and pesto.",
            durationMinutes = 10,
            difficulty = Difficulty.EASY,
            imageName = "caprese_avocado",
            baseServings = 2,
            ingredients = listOf(
                Ingredient(name = "ripe avocados", quantity = 2.0),
                Ingredient(name = "cherry tomatoes", quantity = 10.0),
                Ingredient(name = "mini mozzarella balls", quantity = 8.0),
                Ingredient(name = "fresh basil leaves"),
                Ingredient(name = "pesto", unit = "tbsp", quantity = 1.0)
            ),
            steps = listOf(
                "Halve avocados and remove pits.",
                "Toss tomatoes, mozzarella, basil, and pesto.",
                "Spoon mixture into avocado halves and serve."
            )
        ),
        Recipe(
            id = UUID.randomUUID(),
            title = "Smoky BBQ Jackfruit Sandwich",
            summary = "Plant-based pulled “pork” with tangy slaw on toasted buns.",
            durationMinutes = 40,
            difficulty = Difficulty.MEDIUM,
            imageName = "bbq_jackfruit",
            baseServings = 4,
            ingredients = listOf(
                Ingredient(name = "young jackfruit cans", quantity = 2.0),
                Ingredient(name = "BBQ sauce", unit = "cup", quantity = 1.0),
                Ingredient(name = "smoked paprika", unit = "tsp", quantity = 1.0),
                Ingredient(name = "slaw mix", unit = "cup", quantity = 2.0),
                Ingredient(name = "burger buns", quantity = 4.0)
            ),
            steps = listOf(
                "Rinse and shred jackfruit.",
                "Simmer with BBQ sauce and paprika for 20 minutes.",
                "Toast buns, pile jackfruit, and add slaw."
            )
        ),
        Recipe(
            id = UUID.randomUUID(),
            title = "Mystery Chef Special",
            summary = "Experimental dish that keeps crashing the kitchen systems.",
            durationMinutes = 15,
            difficulty = Difficulty.HARD,
            imageName = null,
            baseServings = 2,
            ingredients = listOf(
                Ingredient(name = "???")
            ),
            steps = listOf(
                "System error – steps failed to load."
            )
        )
    )
}
