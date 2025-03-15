package dev.trindadedev.snakegame.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable

@Composable
internal actual fun SnakeTheme(content: @Composable () -> Unit) {
  MaterialTheme(
    colorScheme = AppColorScheme(),
    typography = Typography(),
    content = {
      Surface(content = content)
    },
  )
}
