package dev.trindadedev.snakegame

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier

@Immutable
open class GridItem(open val x: Int, open val y: Int) {
  override fun toString(): String {
    return "GridItem(x=$x, y=$y)"
  }

  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (other !is GridItem) return false
    return x == other.x && y == other.y
  }

  override fun hashCode(): Int {
    return 31 * x + y
  }
}

@Composable
fun SnakeGameGrid(modifier: Modifier = Modifier, snake: Snake, food: Food) {
  Column(modifier = modifier) {
    for (y in 0 until SnakeGameTokens.GridItem.PerRow) {
      Row {
        for (x in 0 until SnakeGameTokens.GridItem.PerRow) {
          Box(
            modifier =
              Modifier.size(SnakeGameTokens.GridItem.Dimens.Size)
                .border(
                  border =
                    BorderStroke(
                      width = SnakeGameTokens.GridItem.Dimens.BorderStrokeWidth,
                      color = SnakeGameTokens.GridItem.Colors.BorderStrokeColor,
                    )
                )
                .background(
                  when (GridItem(x, y)) {
                    in snake.state.body -> SnakeGameTokens.GridItem.Colors.SnakeColor
                    food -> SnakeGameTokens.GridItem.Colors.FoodColor
                    else -> SnakeGameTokens.GridItem.Colors.EmptyColor
                  }
                )
          )
        }
      }
    }
  }
}
