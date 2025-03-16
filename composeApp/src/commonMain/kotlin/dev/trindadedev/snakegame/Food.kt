package dev.trindadedev.snakegame

import kotlin.random.Random

fun generateFood(
  snake: List<Cell>
): Cell {
  val emptyCells = (0 until SnakeGameTokens.Cell.PerRow).flatMap { x ->
    (0 until SnakeGameTokens.Cell.PerRow).map { y ->
      Cell(x, y) 
    }
  }
  return emptyCells[Random.nextInt(emptyCells.size)]
}