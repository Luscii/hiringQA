import SwiftUI

@main
struct CookItApp: App {
    @StateObject private var store = RecipeStore()

    var body: some Scene {
        WindowGroup {
            NavigationStack {
                RecipeListView()
            }
            .environmentObject(store)
        }
    }
}
