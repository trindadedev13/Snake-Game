package dev.trindadedev.snakegame

import androidx.compose.material.icons.Icons as MIcons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material.icons.rounded.Pause
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

object SnakeGameTokens {
  /** values used in game cells */
  object GridItem {
    val PerRow = 20

    object Dimens {
      val Size = 16.dp
      val BorderStrokeWidth = 0.2.dp
    }

    /** Colors used in game cells * */
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

  object DirectionButtons {
    object Icons {
      val Down = MIcons.Rounded.KeyboardArrowDown
      val Left = MIcons.Rounded.KeyboardArrowLeft
      val Right = MIcons.Rounded.KeyboardArrowRight
      val Up = MIcons.Rounded.KeyboardArrowUp
      val Pause = MIcons.Rounded.Pause
      val Play = MIcons.Rounded.PlayArrow
    }
  }
}
