import SwiftUI

struct RecipeListView: View {
    @EnvironmentObject private var store: RecipeStore
    @State private var searchText: String = ""

    private var filteredRecipes: [Recipe] {
        guard !searchText.isEmpty else { return store.recipes }
        return store.recipes.filter { recipe in
            recipe.title.localizedCaseInsensitiveContains(searchText) ||
            recipe.summary.localizedCaseInsensitiveContains(searchText)
        }
    }

    var body: some View {
        List(filteredRecipes) { recipe in
            NavigationLink(value: recipe) {
                RecipeRow(recipe: recipe)
            }
        }
        .navigationTitle("Cook it!")
        .navigationDestination(for: Recipe.self) { recipe in
            RecipeDetailView(recipe: recipe)
        }
        .searchable(text: $searchText, prompt: "Search recipes")
        .overlay(content: emptyStateOverlay)
    }

    @ViewBuilder
    private func emptyStateOverlay() -> some View {
        if filteredRecipes.isEmpty {
            ContentUnavailableView("No recipes", systemImage: "fork.knife.circle", description: Text("Try another keyword"))
        }
    }
}

private struct RecipeRow: View {
    let recipe: Recipe

    var body: some View {
        VStack(alignment: .leading, spacing: 6) {
            HStack {
                Text(recipe.title)
                    .font(.headline)
                Spacer()
                Label(recipe.formattedDuration, systemImage: "clock")
                    .font(.caption)
                    .foregroundStyle(.secondary)
            }
            Text(recipe.summary)
                .font(.subheadline)
                .foregroundStyle(.secondary)
            HStack(spacing: 8) {
                DifficultyBadge(level: recipe.difficulty)
                Text("Ingredients: \(recipe.ingredients.count)")
                    .font(.caption)
                    .foregroundStyle(.secondary)
            }
        }
        .padding(.vertical, 4)
    }
}

private struct DifficultyBadge: View {
    let level: Recipe.Difficulty

    var body: some View {
        Text(level.rawValue)
            .font(.caption)
            .padding(.horizontal, 8)
            .padding(.vertical, 4)
            .background(capsuleColor)
            .foregroundStyle(.white)
            .clipShape(Capsule())
    }

    private var capsuleColor: Color {
        switch level {
        case .easy: return .green
        case .medium: return .orange
        case .hard: return .red
        }
    }
}

#Preview {
    NavigationStack {
        RecipeListView()
            .environmentObject(RecipeStore())
    }
}
