# Cook it! – Android Test App

Jetpack Compose implementation of the Cook it! recipes tester application. The Android version matches the iOS feature set, including the intentional stability issue on the final recipe entry.

## Features

- Recipe list with search, duration, difficulty badges, and empty state.
- Detail screen with hero image placeholder, adjustable servings (1–20, capped at 10), ingredient scaling, preparation steps, and feedback dialog (1–5 stars).
- Ratings stored in-memory per session.
- Last recipe intentionally crashes when opened to ensure candidates explore every entry.

## Getting Started

1. Install Android Studio Iguana (AGP 8.5+) or newer.
2. From Android Studio, select **Open** and choose the `Android` folder at the repo root.
3. Sync Gradle when prompted.
4. Select the `app` configuration and run on an Android 13 (API 33) emulator or device.

Command-line build:

```bash
./gradlew assembleDebug
```

> Building from this environment may fail because the sandbox cannot download Gradle distributions; use your local machine for verification.

## Adding Recipe Images

Each sample recipe references an `imageName` (same list as the iOS app):

`margherita`, `risotto`, `quinoa`, `lentil_soup`, `shrimp_pasta`, `green_curry`, `overnight_oats`, `roast_chicken`, `buddha_bowl`, `lava_cake`, `caprese_avocado`, `bbq_jackfruit`, `slow_cooker_chili`

To replace the gradient placeholder, drop PNGs/JPEGs into `app/src/main/res/drawable/` using the exact names. Compose automatically selects them if present.

## Known Quirks (intentional)

- No recipe images load unless you add the assets. You should see empty image placeholders when opening details.
- Exactly 13 recipes ship with the app; opening the final “Mystery Chef Special” crashes the app on purpose.
- Margherita recipe contains the deliberate typo `Pinch of saalt`.
- Servings picker stops scaling ingredients beyond 10 people even if you pick higher numbers.

## Tests

A lightweight unit test (`SampleRecipesTest`) ensures the repository exposes the expected number of seeded recipes. Run with:

```bash
./gradlew test
```
