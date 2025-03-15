package dev.trindadedev.snakegame

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.datetime.Clock

@Composable
fun SnakeGameApp(
  viewModel: SnakeViewModel = viewModel { SnakeViewModel() }
) {
  val uiState = viewModel.uiState
  LaunchedEffect(uiState.gameId) {
    while (!uiState.isGameOver) {
      delay(uiState.gameSpeed)

      val instant = Clock.System.now()
      val dateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
    
      if (getMillis() - uiState.lastMoveTime >= uiState.snakeState) {
        viewModel.setSnake(moveSnake(uiState.snake, uiState.direction))
        viewModel.setLastMoveTime(getMillis())
      }
      if (uiState.snake.first() == uiState.food) {
        viewModel.setFood(generateFood(uiState.snake))
        viewModel.setSnake(growSnake(uiState.snake, uiState.direction))
      }
      viewModel.setIsGameOver(checkGameOver(uiState.snake))
    }
  }
  Column(
    modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
  ) {
    DirectionButtons(
      onClick = { direction ->
        
      }
    )
  }
}

private fun moveSnake(snake: List<Cell>, direction: Direction): List<Cell> {
  val head = snake.first()
  val newHead = when (direction) {
    Direction.UP -> Cell(head.x, head.y - 1)
    Direction.DOWN -> Cell(head.x, head.y + 1)
    Direction.LEFT -> Cell(head.x - 1, head.y)
    Direction.RIGHT -> Cell(head.x + 1, head.y)
  }
  val newSnake = snake.toMutableList()
  newSnake.add(0, newHead)
  newSnake.removeAt(newSnake.size - 1)
  return newSnake
}

private fun growSnake(
  snake: List<Cell>,
  direction: Direction,
  gridSize: Int = SnakeGameConsts.GRID_SIZE
): List<Cell> {
  val growth = when (direction) {
    Direction.UP -> Cell(snake.first().x, (snake.first().y - 1 + gridSize) % gridSize)
    Direction.DOWN -> Cell(snake.first().x, (snake.first().y + 1) % gridSize)
    Direction.LEFT -> Cell((snake.first().x - 1 + gridSize) % gridSize, snake.first().y)
    Direction.RIGHT -> Cell((snake.first().x + 1) % gridSize, snake.first().y)
  }
  return listOf(growth) + snake
}

private fun checkGameOver(
  snake: List<Cell>,
  gridSize: Int = SnakeGameConsts.GRID_SIZE
): Boolean {
  val head = snake.first()
  return head in snake.drop(1) || head.x < 0 || head.y < 0 || head.x >= gridSize || head.y >= gridSize
}

import kotlinx.datetime.*

fun getMillis() {
  val instant = Clock.System.now()
  return instant.toEpochMilliseconds()
}
