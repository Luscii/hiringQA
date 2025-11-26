import Foundation

struct Recipe: Identifiable, Hashable {
    enum Difficulty: String, CaseIterable, Codable {
        case easy = "Easy"
        case medium = "Medium"
        case hard = "Hard"
    }

    struct Ingredient: Identifiable, Hashable {
        let id = UUID()
        let name: String
        let unit: String?
        let quantity: Double?

        init(name: String, unit: String? = nil, quantity: Double? = nil) {
            self.name = name
            self.unit = unit
            self.quantity = quantity
        }

        func description(forServings servings: Int, baseServings: Int) -> String {
            guard let quantity = quantity, baseServings > 0 else {
                return name
            }
            let clampedServings = min(servings, 10)
            let factor = Double(clampedServings) / Double(baseServings)
            let scaled = quantity * factor
            let amount = Ingredient.numberFormatter.string(from: scaled as NSNumber) ?? "\(scaled)"
            let unitPart: String
            if let unit = unit, !unit.isEmpty {
                unitPart = " \(unit)"
            } else {
                unitPart = ""
            }
            return "\(amount)\(unitPart) \(name)".trimmingCharacters(in: .whitespaces)
        }

        private static let numberFormatter: NumberFormatter = {
            let formatter = NumberFormatter()
            formatter.maximumFractionDigits = 2
            formatter.minimumFractionDigits = 0
            formatter.locale = .current
            return formatter
        }()
    }

    let id: UUID
    let title: String
    let summary: String
    let duration: Int
    let difficulty: Difficulty
    let imageName: String?
    let baseServings: Int
    let ingredients: [Ingredient]
    let steps: [String]

    var formattedDuration: String {
        "\(duration) min"
    }
}
