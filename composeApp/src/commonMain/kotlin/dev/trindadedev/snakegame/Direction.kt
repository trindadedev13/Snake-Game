package dev.trindadedev.snakegame

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.painter.Painter
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import snake.composeapp.generated.resources.Res
import snake.composeapp.generated.resources.ic_down
import snake.composeapp.generated.resources.ic_left
import snake.composeapp.generated.resources.ic_right
import snake.composeapp.generated.resources.ic_up
import snake.composeapp.generated.resources.str_up
import snake.composeapp.generated.resources.str_down
import snake.composeapp.generated.resources.str_left
import snake.composeapp.generated.resources.str_right

@Composable
fun DirectionButtons(onClick: (Direction) -> Unit) {
  DirectionButton(
    name = stringResource(resource = Res.string.str_up),
    icon = painterResource(resource = Res.drawable.ic_up),
    onClick = { onClick(Direction.UP) }
  )
  Row(horizontalArrangement = Arrangement.spacedBy(-8.dp)) {
    DirectionButton(
      name = stringResource(resource = Res.string.str_left),
      icon = painterResource(resource = Res.drawable.ic_left),
      onClick = { onClick(Direction.LEFT) }
    )
    DirectionButton(
      name = stringResource(resource = Res.string.str_right),
      icon = painterResource(resource = Res.drawable.ic_right),
      onClick = { onClick(Direction.RIGHT) }
    )
  }
  DirectionButton(
    name = stringResource(resource = Res.string.str_down),
    icon = painterResource(resource = Res.drawable.ic_down),
    onClick = { onClick(Direction.DOWN) }
  )
}

@Composable
private fun DirectionButton(
  name: String,
  icon: Painter,
  onClick: () -> Unit
) {
  Image(
    painter = icon,
    contentDescription = name,
    modifier = Modifier
      .size(70.dp)
      .clickable(onClick = onClick, onClickLabel = name)
  )
}

enum class Direction {
  UP,
  DOWN,
  LEFT,
  RIGHT;

  fun isOpposite(other: Direction)= when (this) {
    UP -> other == DOWN
    DOWN -> other == UP
    LEFT -> other == RIGHT
    RIGHT -> other == LEFT
  }
}