package dev.trindadedev.snakegame

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

object SnakeGameTokens {
  /** values used in game cells */
  object Cell {
    val PerRow = 20
    val Size = 16.dp
    val BorderStrokeWidth = 0.2.dp

    /** Colors used in game cells **/
    object Colors {
      val BorderStrokeColor: Color
        @Composable get() = MaterialTheme.colorScheme.outlineVariant
      val SnakeColor: Color
        @Composable get() = MaterialTheme.colorScheme.primary
      val FoodColor: Color
        @Composable get() = MaterialTheme.colorScheme.tertiary
      val EmptyColor: Color
        @Composable get() = MaterialTheme.colorScheme.surfaceContainerHighest
    }
  }
}