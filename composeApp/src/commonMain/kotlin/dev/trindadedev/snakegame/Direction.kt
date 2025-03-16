package dev.trindadedev.snakegame

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import snake.composeapp.generated.resources.Res
import snake.composeapp.generated.resources.ic_down
import snake.composeapp.generated.resources.ic_left
import snake.composeapp.generated.resources.ic_right
import snake.composeapp.generated.resources.ic_up
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
fun DirectionButtons(onClick: (Direction) -> Unit) {
  DirectionButton(
    name = stringResource(resource = Res.string.str_up),
    icon = painterResource(resource = Res.drawable.ic_up),
    onClick = { onClick(Direction.UP) },
  )
  Row(horizontalArrangement = Arrangement.spacedBy(-8.dp)) {
    DirectionButton(
      name = stringResource(resource = Res.string.str_left),
      icon = painterResource(resource = Res.drawable.ic_left),
      onClick = { onClick(Direction.LEFT) },
    )
    Spacer(modifier = Modifier.size(70.dp))
    DirectionButton(
      name = stringResource(resource = Res.string.str_right),
      icon = painterResource(resource = Res.drawable.ic_right),
      onClick = { onClick(Direction.RIGHT) },
    )
  }
  DirectionButton(
    name = stringResource(resource = Res.string.str_down),
    icon = painterResource(resource = Res.drawable.ic_down),
    onClick = { onClick(Direction.DOWN) },
  )
}

/**
 * Button used to move Snake.
 *
 * @param name The name of Actionz for Accessibility.
 * @param icon The PainterResource of icon.
 * @param onClick The lambda called when user click in it.
 */
@Composable
private fun DirectionButton(name: String, icon: Painter, onClick: () -> Unit) {
  Image(
    painter = icon,
    contentDescription = name,
    modifier = Modifier.size(70.dp).clickable(onClick = onClick, onClickLabel = name),
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
