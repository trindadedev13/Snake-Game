package dev.trindadedev.snakegame

import kotlin.random.Random

fun generateFood(
  snake: List<Cell>,
  gridSize: Int = SnakeGameConsts.GRID_SIZE
): Cell {
  val emptyCells = (0 until gridSize).flatMap { x ->
    (0 until gridSize).map { y ->
      Cell(x, y) 
    }
  }
  return emptyCells[Random.nextInt(emptyCells.size)]
}