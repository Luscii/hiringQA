import XCTest
@testable import CookIt

final class CookItTests: XCTestCase {
    func testSampleRecipesAvailability() throws {
        let store = RecipeStore()
        XCTAssertEqual(store.recipes.count, 13, "Expecting thirteen seeded recipes for testers")
    }

    func testRecipeFieldsArePopulated() throws {
        let recipe = SampleRecipes.list.first
        XCTAssertNotNil(recipe)
        XCTAssertFalse(recipe?.ingredients.isEmpty ?? true)
        XCTAssertFalse(recipe?.steps.isEmpty ?? true)
    }
}
