package dev.trindadedev.snakegame

import androidx.compose.runtime.Immutable

@Immutable
data class SnakeUIState(
  val currentDirection: Direction = Direction.UP,
  val snake: List<Cell> = listOf(Cell(5, 5)),
  val food: Cell = generateFood(snake),
  val gameSpeed: Long = 50L,
  val snakeSpeed: Long = 200L,
  val lastMoveTime: Long = 0L
)