import Foundation
import SwiftUI

final class RecipeStore: ObservableObject {
    @Published private(set) var recipes: [Recipe] = SampleRecipes.list
    @Published private(set) var ratings: [UUID: Int] = [:]

    func rating(for recipe: Recipe) -> Int {
        ratings[recipe.id] ?? 0
    }

    func setRating(_ value: Int, for recipe: Recipe) {
        ratings[recipe.id] = value
    }
}

enum SampleRecipes {
    private static let builtRecipes: [Recipe] = SampleRecipes.makeRecipes()
    static let list: [Recipe] = builtRecipes
    private static let stabilityTestRecipeID: UUID? = builtRecipes.last?.id

    static func shouldTriggerStabilityTest(for recipe: Recipe) -> Bool {
        stabilityTestRecipeID == recipe.id
    }
}

private extension SampleRecipes {
    static func makeRecipes() -> [Recipe] {
        [
        Recipe(
            id: UUID(),
            title: "Classic Margherita Pizza",
            summary: "Crispy thin crust with fresh tomato sauce, mozzarella, and basil.",
            duration: 35,
            difficulty: .medium,
            imageName: "margherita",
            baseServings: 2,
            ingredients: [
                Recipe.Ingredient(name: "pizza dough ball", quantity: 1),
                Recipe.Ingredient(name: "tomato passata", unit: "cup", quantity: 0.5),
                Recipe.Ingredient(name: "fresh mozzarella", unit: "g", quantity: 120),
                Recipe.Ingredient(name: "fresh basil leaves"),
                Recipe.Ingredient(name: "olive oil", unit: "tbsp", quantity: 1),
                Recipe.Ingredient(name: "Pinch of saslt")
            ],
            steps: [
                "Preheat oven to 250ºC with a pizza stone if available.",
                "Stretch dough and spread tomato passata evenly.",
                "Top with torn mozzarella, drizzle oil, and season.",
                "Bake 8-10 minutes until crust is golden.",
                "Finish with fresh basil before serving."
            ]
        ),
        Recipe(
            id: UUID(),
            title: "Creamy Mushroom Risotto",
            summary: "Silky arborio rice slowly cooked with mushroom stock and parmesan.",
            duration: 45,
            difficulty: .hard,
            imageName: "risotto",
            baseServings: 4,
            ingredients: [
                Recipe.Ingredient(name: "arborio rice", unit: "cup", quantity: 1),
                Recipe.Ingredient(name: "mixed mushrooms", unit: "g", quantity: 250),
                Recipe.Ingredient(name: "dry white wine", unit: "cup", quantity: 0.5),
                Recipe.Ingredient(name: "vegetable stock", unit: "cup", quantity: 4),
                Recipe.Ingredient(name: "butter", unit: "tbsp", quantity: 2),
                Recipe.Ingredient(name: "grated parmesan", unit: "cup", quantity: 0.33)
            ],
            steps: [
                "Sauté mushrooms in butter until browned; set aside.",
                "Toast rice in the same pan, then deglaze with wine.",
                "Add warm stock one ladle at a time, stirring often.",
                "Fold in mushrooms and parmesan once rice is al dente.",
                "Rest 2 minutes before serving creamy."
            ]
        ),
        Recipe(
            id: UUID(),
            title: "Citrus Quinoa Salad",
            summary: "Bright salad with fluffy quinoa, orange segments, and crunchy greens.",
            duration: 20,
            difficulty: .easy,
            imageName: "quinoa",
            baseServings: 2,
            ingredients: [
                Recipe.Ingredient(name: "cooked quinoa", unit: "cup", quantity: 1),
                Recipe.Ingredient(name: "oranges, segmented", quantity: 2),
                Recipe.Ingredient(name: "cucumber, diced", quantity: 0.5),
                Recipe.Ingredient(name: "baby spinach (handful)"),
                Recipe.Ingredient(name: "toasted almonds", unit: "tbsp", quantity: 2),
                Recipe.Ingredient(name: "citrus vinaigrette")
            ],
            steps: [
                "Fluff cold quinoa with a fork.",
                "Combine quinoa, oranges, cucumber, spinach, and almonds.",
                "Drizzle with vinaigrette and toss gently.",
                "Season with salt and serve immediately."
            ]
        ),
        Recipe(
            id: UUID(),
            title: "Spicy Lentil Soup",
            summary: "Comforting tomato-based soup with red lentils and warm spices.",
            duration: 30,
            difficulty: .easy,
            imageName: "lentil_soup",
            baseServings: 4,
            ingredients: [
                Recipe.Ingredient(name: "red lentils", unit: "cup", quantity: 1),
                Recipe.Ingredient(name: "yellow onion, diced", quantity: 1),
                Recipe.Ingredient(name: "garlic cloves", quantity: 2),
                Recipe.Ingredient(name: "ground cumin", unit: "tsp", quantity: 1),
                Recipe.Ingredient(name: "smoked paprika", unit: "tsp", quantity: 1),
                Recipe.Ingredient(name: "vegetable broth", unit: "cup", quantity: 4)
            ],
            steps: [
                "Sauté onion and garlic until translucent.",
                "Add spices and cook until fragrant.",
                "Pour in lentils and broth, simmer 20 minutes.",
                "Blend partially for creamier texture and serve."
            ]
        ),
        Recipe(
            id: UUID(),
            title: "Garlic Butter Shrimp Pasta",
            summary: "Juicy shrimp tossed with linguine, garlic butter, and lemon.",
            duration: 25,
            difficulty: .medium,
            imageName: "shrimp_pasta",
            baseServings: 2,
            ingredients: [
                Recipe.Ingredient(name: "linguine", unit: "g", quantity: 200),
                Recipe.Ingredient(name: "shrimp, peeled", unit: "g", quantity: 300),
                Recipe.Ingredient(name: "garlic cloves", quantity: 3),
                Recipe.Ingredient(name: "butter", unit: "tbsp", quantity: 3),
                Recipe.Ingredient(name: "lemon, juiced", quantity: 1),
                Recipe.Ingredient(name: "fresh parsley", unit: "tbsp", quantity: 2)
            ],
            steps: [
                "Cook pasta al dente and reserve a cup of water.",
                "Sear shrimp in butter and garlic until pink.",
                "Add lemon juice, pasta, and a splash of pasta water.",
                "Toss with parsley and serve hot."
            ]
        ),
        Recipe(
            id: UUID(),
            title: "Thai Green Curry",
            summary: "Aromatic coconut curry with vegetables and basil.",
            duration: 35,
            difficulty: .medium,
            imageName: "green_curry",
            baseServings: 4,
            ingredients: [
                Recipe.Ingredient(name: "green curry paste", unit: "tbsp", quantity: 2),
                Recipe.Ingredient(name: "coconut milk", unit: "ml", quantity: 400),
                Recipe.Ingredient(name: "mixed vegetables", unit: "cup", quantity: 1),
                Recipe.Ingredient(name: "firm tofu or chicken", unit: "g", quantity: 250),
                Recipe.Ingredient(name: "fresh basil"),
                Recipe.Ingredient(name: "fish sauce", unit: "tbsp", quantity: 1)
            ],
            steps: [
                "Fry curry paste in coconut cream until fragrant.",
                "Add protein and vegetables, toss to coat.",
                "Pour remaining coconut milk and simmer 15 minutes.",
                "Finish with fish sauce and basil."
            ]
        ),
        Recipe(
            id: UUID(),
            title: "Blueberry Overnight Oats",
            summary: "Make-ahead breakfast with oats, chia, yogurt, and berries.",
            duration: 10,
            difficulty: .easy,
            imageName: "overnight_oats",
            baseServings: 1,
            ingredients: [
                Recipe.Ingredient(name: "rolled oats", unit: "cup", quantity: 0.5),
                Recipe.Ingredient(name: "chia seeds", unit: "tbsp", quantity: 1),
                Recipe.Ingredient(name: "milk", unit: "cup", quantity: 0.5),
                Recipe.Ingredient(name: "yogurt", unit: "cup", quantity: 0.25),
                Recipe.Ingredient(name: "blueberries"),
                Recipe.Ingredient(name: "honey", unit: "tsp", quantity: 1)
            ],
            steps: [
                "Combine oats, chia, milk, yogurt, and honey.",
                "Stir in blueberries and refrigerate overnight.",
                "Top with extra fruit before serving."
            ]
        ),
        Recipe(
            id: UUID(),
            title: "Herb Roasted Chicken",
            summary: "Succulent roast chicken with crispy skin and fresh herbs.",
            duration: 70,
            difficulty: .medium,
            imageName: "roast_chicken",
            baseServings: 4,
            ingredients: [
                Recipe.Ingredient(name: "whole chicken", quantity: 1),
                Recipe.Ingredient(name: "butter", unit: "tbsp", quantity: 2),
                Recipe.Ingredient(name: "fresh thyme sprigs", quantity: 4),
                Recipe.Ingredient(name: "fresh rosemary sprigs", quantity: 2),
                Recipe.Ingredient(name: "garlic cloves", quantity: 4),
                Recipe.Ingredient(name: "lemon", quantity: 1)
            ],
            steps: [
                "Pat chicken dry and stuff with herbs and lemon.",
                "Rub butter and garlic under the skin.",
                "Roast at 200ºC for 60 minutes or until juices run clear.",
                "Rest 10 minutes before carving."
            ]
        ),
        Recipe(
            id: UUID(),
            title: "Vegan Buddha Bowl",
            summary: "Colorful bowl with roasted veggies, grains, and tahini drizzle.",
            duration: 30,
            difficulty: .easy,
            imageName: "buddha_bowl",
            baseServings: 2,
            ingredients: [
                Recipe.Ingredient(name: "cooked brown rice", unit: "cup", quantity: 1),
                Recipe.Ingredient(name: "roasted sweet potato cubes", unit: "cup", quantity: 1),
                Recipe.Ingredient(name: "steamed broccoli florets", unit: "cup", quantity: 1),
                Recipe.Ingredient(name: "chickpeas", unit: "cup", quantity: 1),
                Recipe.Ingredient(name: "avocado", quantity: 1),
                Recipe.Ingredient(name: "tahini dressing", unit: "tbsp", quantity: 3)
            ],
            steps: [
                "Arrange rice and vegetables in a shallow bowl.",
                "Add chickpeas and avocado.",
                "Drizzle with tahini dressing and serve."
            ]
        ),
        Recipe(
            id: UUID(),
            title: "Chocolate Lava Cake",
            summary: "Individual cakes with a gooey molten chocolate center.",
            duration: 25,
            difficulty: .hard,
            imageName: "lava_cake",
            baseServings: 4,
            ingredients: [
                Recipe.Ingredient(name: "dark chocolate", unit: "g", quantity: 170),
                Recipe.Ingredient(name: "butter", unit: "cup", quantity: 0.5),
                Recipe.Ingredient(name: "eggs", quantity: 2),
                Recipe.Ingredient(name: "egg yolks", quantity: 2),
                Recipe.Ingredient(name: "sugar", unit: "cup", quantity: 0.33),
                Recipe.Ingredient(name: "flour", unit: "cup", quantity: 0.25),
                Recipe.Ingredient(name: "Pinch of salt")
            ],
            steps: [
                "Melt chocolate and butter together.",
                "Whisk eggs and sugar until pale, fold into chocolate.",
                "Add flour and salt, then pour into buttered ramekins.",
                "Bake 12 minutes until edges set but center jiggles."
            ]
        ),
        Recipe(
            id: UUID(),
            title: "Caprese Stuffed Avocado",
            summary: "Ripe avocados filled with mozzarella, tomato, and pesto.",
            duration: 10,
            difficulty: .easy,
            imageName: "caprese_avocado",
            baseServings: 2,
            ingredients: [
                Recipe.Ingredient(name: "ripe avocados", quantity: 2),
                Recipe.Ingredient(name: "cherry tomatoes", quantity: 10),
                Recipe.Ingredient(name: "mini mozzarella balls", quantity: 8),
                Recipe.Ingredient(name: "fresh basil leaves"),
                Recipe.Ingredient(name: "pesto", unit: "tbsp", quantity: 1)
            ],
            steps: [
                "Halve avocados and remove pits.",
                "Toss tomatoes, mozzarella, basil, and pesto.",
                "Spoon mixture into avocado halves and serve."
            ]
        ),
        Recipe(
            id: UUID(),
            title: "Smoky BBQ Jackfruit Sandwich",
            summary: "Plant-based pulled “pork” with tangy slaw on toasted buns.",
            duration: 40,
            difficulty: .medium,
            imageName: "bbq_jackfruit",
            baseServings: 4,
            ingredients: [
                Recipe.Ingredient(name: "young jackfruit cans", quantity: 2),
                Recipe.Ingredient(name: "BBQ sauce", unit: "cup", quantity: 1),
                Recipe.Ingredient(name: "smoked paprika", unit: "tsp", quantity: 1),
                Recipe.Ingredient(name: "slaw mix", unit: "cup", quantity: 2),
                Recipe.Ingredient(name: "burger buns", quantity: 4)
            ],
            steps: [
                "Rinse and shred jackfruit.",
                "Simmer with BBQ sauce and paprika for 20 minutes.",
                "Toast buns, pile jackfruit, and add slaw."
            ]
        ),
        Recipe(
            id: UUID(),
            title: "Slow Cooker Chili",
            summary: "Hearty beef and bean chili that simmers for hours until rich.",
            duration: 480,
            difficulty: .medium,
            imageName: "slow_cooker_chili",
            baseServings: 6,
            ingredients: [
                Recipe.Ingredient(name: "ground beef", unit: "g", quantity: 500),
                Recipe.Ingredient(name: "kidney beans, drained", unit: "cup", quantity: 2),
                Recipe.Ingredient(name: "diced tomatoes", unit: "cup", quantity: 2),
                Recipe.Ingredient(name: "onion, chopped", quantity: 1),
                Recipe.Ingredient(name: "garlic cloves", quantity: 3),
                Recipe.Ingredient(name: "chili powder", unit: "tbsp", quantity: 2),
                Recipe.Ingredient(name: "cumin", unit: "tsp", quantity: 1),
                Recipe.Ingredient(name: "beef broth", unit: "cup", quantity: 1)
            ],
            steps: [
                "Brown beef with onion and garlic.",
                "Transfer to slow cooker with beans, tomatoes, spices, and broth.",
                "Cook on low 6-8 hours, stirring once halfway.",
                "Serve with lime, cheese, or sour cream."
            ]
        )
    ]
    }
}
