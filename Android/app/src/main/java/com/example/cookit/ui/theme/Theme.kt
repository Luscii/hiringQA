package com.example.cookit.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Color(0xFFF24D51),
    onPrimary = Color.White,
    secondary = Color(0xFF4CAF50),
    background = Color(0xFFFFFBFE),
    surface = Color.White,
    onSurface = Color(0xFF1C1B1F)
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFFEF9A9A),
    onPrimary = Color.Black,
    secondary = Color(0xFF81C784)
)

@Composable
fun CookItTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = MaterialTheme.typography,
        content = content
    )
}
