package dev.trindadedev.snakegame

import androidx.compose.runtime.Immutable

@Immutable
data class SnakeUIState(
  val snake: Snake = Snake(),
  val food: Food = generateFood(),
  val gameSpeed: Long = 50L,
  val snakeSpeed: Long = 200L,
  val lastMoveTime: Long = 0L,
  val isPaused: Boolean = false
)
