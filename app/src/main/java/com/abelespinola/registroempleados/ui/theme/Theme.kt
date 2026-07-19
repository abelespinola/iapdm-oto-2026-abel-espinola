package com.abelespinola.registroempleados.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = TealPrimary, onPrimary = Color.White, background = LightBackground,
    surface = LightSurface, surfaceVariant = LightSurfaceVariant, onSurfaceVariant = TealDark
)

private val DarkColorScheme = darkColorScheme(
    primary = TealPrimary, onPrimary = Color.White, background = DarkBackground,
    surface = DarkSurface, surfaceVariant = DarkSurfaceVariant, onSurfaceVariant = Color.White
)

@Composable
fun NeuraTechTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme, typography = Typography, content = content)
}