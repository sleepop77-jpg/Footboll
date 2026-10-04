package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = NeonCyan,
    onPrimary = Color(0xFF00382E),
    primaryContainer = Color(0xFF005143),
    onPrimaryContainer = NeonCyanLight,
    secondary = GoldenTrophy,
    onSecondary = Color(0xFF432C00),
    secondaryContainer = Color(0xFF604100),
    onSecondaryContainer = GoldenTrophyLight,
    tertiary = SapphireBlue,
    onTertiary = Color.White,
    background = PitchDark,
    onBackground = TextPrimaryDark,
    surface = PitchDarkSurface,
    onSurface = TextPrimaryDark,
    surfaceVariant = PitchDarkSurfaceVariant,
    onSurfaceVariant = TextSecondaryDark,
    surfaceTint = NeonCyan,
    error = CrimsonAlert,
    onError = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF00897B),
    onPrimary = Color.White,
    primaryContainer = Color(0xFFA7F3D0),
    onPrimaryContainer = Color(0xFF00201A),
    secondary = Color(0xFFD97706),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFFEF3C7),
    onSecondaryContainer = Color(0xFF451A03),
    tertiary = SapphireBlue,
    onTertiary = Color.White,
    background = PitchLight,
    onBackground = TextPrimaryLight,
    surface = PitchLightSurface,
    onSurface = TextPrimaryLight,
    surfaceVariant = PitchLightSurfaceVariant,
    onSurfaceVariant = TextSecondaryLight,
    error = CrimsonAlert,
    onError = Color.White
)

@Composable
fun FootyPulseTheme(
    darkTheme: Boolean = true, // Default to sleek stadium night dark mode for football atmosphere
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

// Backward compatibility alias
@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {
    FootyPulseTheme(darkTheme = true, dynamicColor = false, content = content)
}
