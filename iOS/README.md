# Cook it! – iOS Test App

Simple SwiftUI application used to evaluate tester candidates. It offers:

- Recipe list with search, difficulty badges, and empty state.
- Detail screen with hero image, rating summary, adjustable servings, ingredients, and steps.
- Feedback sheet with a 1–5 star rating system stored locally.

## Getting Started

1. Open `CookIt.xcodeproj` in Xcode 15 or newer.
2. Select the `CookIt` scheme and an iPhone simulator (or your device).
3. Press `Cmd + R` to build and run.

No external dependencies are required.

## Adding Recipe Images

Each sample recipe references an `imageName` (e.g., `margherita`, `risotto`, `quinoa`, `lentil_soup`, `shrimp_pasta`, `green_curry`, `overnight_oats`, `roast_chicken`, `buddha_bowl`, `lava_cake`, `caprese_avocado`, `bbq_jackfruit`, `slow_cooker_chili`).  
To show real photos:

1. In Xcode’s Project navigator, expand `CookIt/Assets.xcassets`.
2. Create a new **Image Set** for each recipe and name it exactly like the `imageName`.
   - Alternatively, from Finder copy your files into  
     `iOS/CookIt/Assets.xcassets/<ImageName>.imageset/`.
3. Drop your PNG/JPEG variants (`1x`, `2x`, `3x`) into the set.

Until images are provided, the UI falls back to a gradient placeholder with the recipe title.

## Servings Picker

- Each recipe defines a `baseServings`.
- Use the picker on the detail screen to choose between 1 and 20 people.
