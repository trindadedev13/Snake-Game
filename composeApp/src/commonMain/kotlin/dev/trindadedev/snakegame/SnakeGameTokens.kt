package dev.trindadedev.snakegame

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

object SnakeGameTokens {
  val GridSize = 20

  object Cell {
    val Size = 16.dp
    val BorderStrokeWidth = 0.2.dp
    
    object Colors {
      val BorderStrokeColor = Color.LightGrey
      val SnakeColor = Color.Green
      val FoodColor = Color.Red
      val EmptyColor = Color.White
    }
  }
}