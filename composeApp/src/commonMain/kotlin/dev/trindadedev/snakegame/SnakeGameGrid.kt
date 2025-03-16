package dev.trindadedev.snakegame

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun SnakeGameGrid(
  snake: List<Cell>,
  food: Cell,
) {
  Column {
    for (i in 0 until SnakeGameTokens.Cell.PerRow) {
      Row {
        for (j in 0 until SnakeGameTokens.Cell.PerRow) {
          Box(
            modifier = Modifier
              .size(SnakeGameTokens.Cell.Size)
              .border(
                border = BorderStroke(
                  width = SnakeGameTokens.Cell.BorderStrokeWidth,
                  color = SnakeGameTokens.Cell.Colors.BorderStrokeColor
                )
              )
              .background(
                when (Cell(j, i)) {
                  in snake -> SnakeGameTokens.Cell.Colors.SnakeColor
                  food -> SnakeGameTokens.Cell.Colors.FoodColor
                  else -> SnakeGameTokens.Cell.Colors.EmptyColor
                }
              )
          )
        }
      }
    }
  }
}