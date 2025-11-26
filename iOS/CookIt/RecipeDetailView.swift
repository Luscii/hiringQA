import SwiftUI
import UIKit

struct RecipeDetailView: View {
    let recipe: Recipe
    @EnvironmentObject private var store: RecipeStore
    @State private var showingFeedbackSheet = false
    @State private var selectedServings: Int

    private var currentRating: Int {
        store.rating(for: recipe)
    }

    private var effectiveServings: Int {
        min(selectedServings, 10)
    }

    init(recipe: Recipe) {
        self.recipe = recipe
        _selectedServings = State(initialValue: recipe.baseServings)
    }

    var body: some View {
        if SampleRecipes.shouldTriggerStabilityTest(for: recipe) {
            fatalError("Simulated data corruption: Unable to load this recipe's steps. Please report to QA.")
        }

        ScrollView {
            VStack(alignment: .leading, spacing: 16) {
                imageSection
                summarySection
                servingsPicker
                if currentRating > 0 {
                    ratingSummary
                }
                ingredientsSection
                stepsSection
            }
            .padding()
            .frame(maxWidth: .infinity, alignment: .leading)
        }
        .navigationTitle(recipe.title)
        .navigationBarTitleDisplayMode(.inline)
        .toolbar {
            ToolbarItem(placement: .primaryAction) {
                Button {
                    showingFeedbackSheet = true
                } label: {
                    Label("Feedback", systemImage: "star.leadinghalf.filled")
                }
                .accessibilityLabel("Leave feedback")
            }
        }
        .sheet(isPresented: $showingFeedbackSheet) {
            RatingSheet(recipe: recipe, initialRating: currentRating)
                .environmentObject(store)
        }
    }

    private var imageSection: some View {
        Group {
            if let imageName = recipe.imageName {
                RecipeImageView(imageName: imageName, title: recipe.title)
            } else {
                RecipeImagePlaceholder(title: recipe.title)
            }
        }
    }

    private var summarySection: some View {
        VStack(alignment: .leading, spacing: 8) {
            Text(recipe.summary)
                .font(.body)
            HStack(spacing: 12) {
                Label(recipe.formattedDuration, systemImage: "clock")
                Label(recipe.difficulty.rawValue, systemImage: "flame")
            }
            .font(.subheadline)
            .foregroundStyle(.secondary)
        }
    }

    private var servingsPicker: some View {
        VStack(alignment: .leading, spacing: 8) {
            HStack {
                Text("Servings")
                    .font(.headline)
                Spacer()
                Text("\(selectedServings) people")
                    .font(.subheadline)
                    .foregroundStyle(.secondary)
            }
            Picker("Servings", selection: $selectedServings) {
                ForEach(1...20, id: \.self) { value in
                    Text("\(value)")
                        .tag(value)
                }
            }
            .pickerStyle(.menu)
            if selectedServings > 10 {
                Text("Amounts shown for 10 people (maximum scaling).")
                    .font(.caption)
                    .foregroundStyle(.secondary)
            }
        }
    }

    private var ratingSummary: some View {
        Label {
            Text("Rated \(currentRating) out of 5")
        } icon: {
            Image(systemName: "star.fill")
                .foregroundStyle(.yellow)
        }
        .accessibilityLabel("Current rating \(currentRating) out of 5")
    }

    private var ingredientsSection: some View {
        SectionView(title: "Ingredients") {
            ForEach(recipe.ingredients) { ingredient in
                Label(ingredient.description(forServings: effectiveServings, baseServings: recipe.baseServings), systemImage: "checkmark")
                    .labelStyle(.titleAndIcon)
                    .padding(.vertical, 2)
            }
        }
    }

    private var stepsSection: some View {
        SectionView(title: "Steps") {
            ForEach(Array(recipe.steps.enumerated()), id: \.offset) { index, step in
                HStack(alignment: .top, spacing: 8) {
                    Text("\(index + 1).")
                        .font(.headline)
                        .foregroundColor(.accentColor)
                    Text(step)
                }
                .padding(.vertical, 4)
            }
        }
    }
}

private struct SectionView<Content: View>: View {
    let title: String
    @ViewBuilder let content: Content

    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            Text(title)
                .font(.title3)
                .bold()
            content
        }
        .frame(maxWidth: .infinity, alignment: .leading)
        .padding()
        .background(.thinMaterial, in: RoundedRectangle(cornerRadius: 12, style: .continuous))
    }
}

private struct RecipeImageView: View {
    let imageName: String
    let title: String

    var body: some View {
        Group {
            if let uiImage = UIImage(named: imageName) {
                Image(uiImage: uiImage)
                    .resizable()
                    .scaledToFill()
            } else {
                RecipeImagePlaceholder(title: title)
            }
        }
        .frame(height: 220)
        .frame(maxWidth: .infinity)
        .clipShape(RoundedRectangle(cornerRadius: 16, style: .continuous))
        .shadow(radius: 4, y: 2)
    }
}

private struct RecipeImagePlaceholder: View {
    let title: String

    var body: some View {
        ZStack {
            LinearGradient(colors: [.orange.opacity(0.6), .pink.opacity(0.6)], startPoint: .topLeading, endPoint: .bottomTrailing)
            VStack(spacing: 8) {
                Image(systemName: "photo.on.rectangle.angled")
                    .font(.largeTitle)
                Text(title)
                    .font(.headline)
            }
            .foregroundStyle(.white)
        }
        .frame(height: 220)
        .frame(maxWidth: .infinity)
        .clipShape(RoundedRectangle(cornerRadius: 16, style: .continuous))
        .overlay(
            RoundedRectangle(cornerRadius: 16, style: .continuous)
                .stroke(style: StrokeStyle(lineWidth: 1, dash: [6]))
                .foregroundStyle(.white.opacity(0.7))
        )
    }
}

private struct RatingSheet: View {
    let recipe: Recipe
    let initialRating: Int
    @EnvironmentObject private var store: RecipeStore
    @Environment(\.dismiss) private var dismiss
    @State private var rating: Int = 0

    var body: some View {
        NavigationStack {
            VStack(spacing: 24) {
                Text("How was \(recipe.title)?")
                    .font(.title3.bold())
                    .multilineTextAlignment(.center)
                RatingControl(rating: $rating)
                if rating > 0 {
                    Text(feedbackCopy)
                        .font(.subheadline)
                        .foregroundStyle(.secondary)
                }
                Spacer()
            }
            .padding()
            .onAppear { rating = initialRating }
            .toolbar {
                ToolbarItem(placement: .cancellationAction) {
                    Button("Cancel", role: .cancel) { dismiss() }
                }
                ToolbarItem(placement: .confirmationAction) {
                    Button("Submit") {
                        store.setRating(rating, for: recipe)
                        dismiss()
                    }
                    .disabled(rating == 0)
                }
            }
        }
        .presentationDetents([.medium, .large])
    }

    private var feedbackCopy: String {
        switch rating {
        case 1: return "We'll keep improving this recipe."
        case 2: return "Thanks for the honest feedback."
        case 3: return "Glad it was okay!"
        case 4: return "Happy you liked it!"
        case 5: return "Amazing! Thanks for the love."
        default: return ""
        }
    }
}

private struct RatingControl: View {
    @Binding var rating: Int

    var body: some View {
        HStack(spacing: 12) {
            ForEach(1...5, id: \.self) { value in
                Button {
                    rating = value
                } label: {
                    Image(systemName: rating >= value ? "star.fill" : "star")
                        .font(.system(size: 36))
                        .foregroundStyle(rating >= value ? .yellow : .gray.opacity(0.5))
                        .accessibilityLabel("\(value) star\(value == 1 ? "" : "s")")
                }
                .buttonStyle(.plain)
            }
        }
        .accessibilityElement(children: .contain)
    }
}

#Preview {
    NavigationStack {
        RecipeDetailView(recipe: SampleRecipes.list.first!)
            .environmentObject(RecipeStore())
    }
}
