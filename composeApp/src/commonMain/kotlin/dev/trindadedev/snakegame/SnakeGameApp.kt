package dev.trindadedev.snakegame

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.datetime.Clock
import org.jetbrains.compose.resources.stringResource
import snake.composeapp.generated.resources.Res
import snake.composeapp.generated.resources.str_game_over
import snake.composeapp.generated.resources.str_restart

@Composable
fun SnakeGameApp(
  viewModel: SnakeViewModel = viewModel { SnakeViewModel() }
) {
  val uiState = viewModel.uiState
  LaunchedEffect(uiState) {
    while (true) {
      delay(uiState.gameSpeed)
      if (getMillis() - uiState.lastMoveTime >= uiState.snakeSpeed) {
        viewModel.setSnake(moveSnake(uiState.snake, uiState.currentDirection))
        viewModel.setLastMoveTime(getMillis())
      }
      if (uiState.snake.first() == uiState.food) {
        viewModel.setFood(generateFood(uiState.snake))
        viewModel.setSnake(growSnake(uiState.snake, uiState.currentDirection))
      }
    }
  }
  Box(modifier = Modifier.fillMaxSize()) {
    Column(
      modifier = Modifier.fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center
    ) {
      SnakeGameGrid(uiState.snake, uiState.food)
      Spacer(modifier = Modifier.height(16.dp))
      DirectionButtons(
        onClick = {
          if (!uiState.currentDirection.isOpposite(it)) viewModel.setCurrentDirection(it)
        }
      )
    }
  }
}

private fun moveSnake(
  snake: List<Cell>,
  direction: Direction
): List<Cell> {
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
  direction: Direction
): List<Cell> {
  val growth = when (direction) {
    Direction.UP -> Cell(snake.first().x, (snake.first().y - 1 + SnakeGameTokens.Cell.PerRow) % SnakeGameTokens.Cell.PerRow)
    Direction.DOWN -> Cell(snake.first().x, (snake.first().y + 1) % SnakeGameTokens.Cell.PerRow)
    Direction.LEFT -> Cell((snake.first().x - 1 + SnakeGameTokens.Cell.PerRow) % SnakeGameTokens.Cell.PerRow, snake.first().y)
    Direction.RIGHT -> Cell((snake.first().x + 1) % SnakeGameTokens.Cell.PerRow, snake.first().y)
  }
  return listOf(growth) + snake
}

private fun getMillis(): Long {
  return Clock.System.now().toEpochMilliseconds()
}
