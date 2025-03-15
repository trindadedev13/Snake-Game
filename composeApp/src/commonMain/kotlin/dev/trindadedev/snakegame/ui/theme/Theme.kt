package dev.trindadedev.snakegame.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable internal expect fun SnakeTheme(content: @Composable () -> Unit)

@Composable
internal fun rememberIsSystemInDarkTheme(): Boolean {
  val a = isSystemInDarkTheme()
  return remember { a }
}

@Composable
internal fun AppColorScheme(): ColorScheme {
  val darkTheme = rememberIsSystemInDarkTheme()
  return when {
    darkTheme -> DarkColorScheme
    else -> LightColorScheme
  }
}