package dev.trindadedev.snakegame

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
  LaunchedEffect(uiState.gameId) {
    while (!uiState.isGameOver) {
      delay(uiState.gameSpeed)
      if (getMillis() - uiState.lastMoveTime >= uiState.snakeSpeed) {
        viewModel.setSnake(moveSnake(uiState.snake, uiState.currentDirection))
        viewModel.setLastMoveTime(getMillis())
      }
      if (uiState.snake.first() == uiState.food) {
        viewModel.setFood(generateFood(uiState.snake))
        viewModel.setSnake(growSnake(uiState.snake, uiState.currentDirection))
      }
      viewModel.setIsGameOver(checkGameOver(uiState.snake))
    }
  }
  Box(modifier = Modifier.fillMaxSize()) {
    if (!uiState.isGameOver) {
      DirectionButtons(
        onClick = { -> 
          if (it.isOpposite(uiState.currentDirection)) viewModel.setCurrentDirection(it)
        }
      )
      Spacer(modifier = Modifier.height(16.dp))
      GameGrid(uiState.snake, uiState.food)
    } else {
      Text(text = stringResource(resource = Res.string.str_game_over))
      Button(
        onClick = { viewModel.restart() }
      )  {
        Text(text = stringResource(resource = Res.string.str_restart))
      }
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
  direction: Direction,
  gridSize: Int = SnakeGameTokens.GridSize
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
  gridSize: Int = SnakeGameTokens.GridSize
): Boolean {
  val head = snake.first()
  return head in snake.drop(1) || head.x < 0 || head.y < 0 || head.x >= gridSize || head.y >= gridSize
}

private fun getMillis(): Long {
  val instant = Clock.System.now()
  return instant.toEpochMilliseconds()
}
