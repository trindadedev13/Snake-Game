package dev.trindadedev.snakegame

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import snake.composeapp.generated.resources.Res
import snake.composeapp.generated.resources.str_down
import snake.composeapp.generated.resources.str_left
import snake.composeapp.generated.resources.str_right
import snake.composeapp.generated.resources.str_up

/**
 * Buttons to move the Snake.
 *
 * @param onClick The lambda Called when user click to move, returns an Direction.
 */
@Composable
fun SnakeGameDirectionButtons(
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  centerSlot: (@Composable () -> Unit)? = null,
  onClick: (Direction) -> Unit,
) {
  Column(
    modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    SnakeGameDirectionButton(
      name = stringResource(resource = Res.string.str_up),
      icon = SnakeGameTokens.DirectionButtons.Icons.Up,
      onClick = { onClick(Direction.UP) },
      enabled = enabled,
    )
    Row(horizontalArrangement = Arrangement.spacedBy(-8.dp)) {
      SnakeGameDirectionButton(
        name = stringResource(resource = Res.string.str_left),
        icon = SnakeGameTokens.DirectionButtons.Icons.Left,
        onClick = { onClick(Direction.LEFT) },
        enabled = enabled,
      )
      if (centerSlot != null) {
        centerSlot?.invoke()
      } else {
        Spacer(modifier = Modifier.size(SnakeGameTokens.DirectionButtons.Dimens.Size))
      }
      SnakeGameDirectionButton(
        name = stringResource(resource = Res.string.str_right),
        icon = SnakeGameTokens.DirectionButtons.Icons.Right,
        onClick = { onClick(Direction.RIGHT) },
        enabled = enabled,
      )
    }
    SnakeGameDirectionButton(
      name = stringResource(resource = Res.string.str_down),
      icon = SnakeGameTokens.DirectionButtons.Icons.Down,
      onClick = { onClick(Direction.DOWN) },
      enabled = enabled,
    )
  }
}

/**
 * Button used to move Snake.
 *
 * @param name The name of Actionz for Accessibility.
 * @param icon The ImageVector of icon.
 * @param onClick The lambda called when user click in it.
 */
@Composable
internal fun SnakeGameDirectionButton(
  modifier: Modifier = Modifier,
  enabled: Boolean = true,
  name: String,
  icon: ImageVector,
  onClick: () -> Unit,
) {
  Image(
    imageVector = icon,
    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
    contentDescription = name,
    modifier =
      modifier.size(SnakeGameTokens.DirectionButtons.Dimens.Size)
        .clickable(enabled = enabled, onClick = onClick, onClickLabel = name),
  )
}

/** The direction where the snake will walk. */
enum class Direction {
  UP,
  DOWN,
  LEFT,
  RIGHT;

  fun isOpposite(other: Direction): Boolean {
    return when (this) {
      UP -> other == DOWN
      DOWN -> other == UP
      LEFT -> other == RIGHT
      RIGHT -> other == LEFT
    }
  }
}
