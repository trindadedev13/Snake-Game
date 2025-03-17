package dev.trindadedev.snakegame

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.stringResource
import snake.composeapp.generated.resources.Res
import snake.composeapp.generated.resources.str_food_eaten
import snake.composeapp.generated.resources.str_snake_size

/** Displays info about game state. */
@Composable
fun SnakeGameInfo(
  modifier: Modifier = Modifier,
  foodEaten: Int,
  snake: Snake,
) {
  Row(modifier = modifier) {
    SnakeGameInfoItem(
      boxColor = SnakeGameTokens.GridItem.Colors.FoodColor,
      value = foodEaten,
      name = stringResource(resource = Res.string.str_food_eaten),
    )
    SnakeGameInfoItem(
      boxColor = SnakeGameTokens.GridItem.Colors.SnakeColor,
      value = snake.state.body.size,
      name = stringResource(resource = Res.string.str_snake_size),
    )
  }
}

@Composable
private fun SnakeGameInfoItem(
  boxColor: Color,
  value: Int,
  name: String 
) {
  Tooltip(name) {
    Row(
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.Center
    ){
      Box(
        modifier = Modifier
          .size(SnakeGameTokens.GridItem.Dimens.Size * 2)
          .border(
            border =
              BorderStroke(
                width = SnakeGameTokens.GridItem.Dimens.BorderStrokeWidth,
                color = SnakeGameTokens.GridItem.Colors.BorderStrokeColor
              )
          )
          .background(boxColor)
      )
      Text(text = value.toString())
    }
  }
}