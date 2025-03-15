package dev.trindadedev.snakegame

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SnakeViewModel: ViewModel() {
  private var _uiState by mutableStateOf(SnakeUIState())
  val uiState: SnakeUIState
    get() = _uiState

  fun setCurrentDirection(newCurrentDirection: Direction) {
    _uiState = _uiState.copy(currentDirection = newCurrentDirection)
  }

  fun setSnake(newSnake: List<Cell>) {
    _uiState = _uiState.copy(snake = newSnake)
  }

  fun setFood(newFood: Cell) {
    _uiState = _uiState.copy(food = newFood)
  }

  fun setGameSpeed(newGameSpeed: Long) {
    _uiState = _uiState.copy(gameSpeed = newGameSpeed)
  }

  fun setSnakeSpeed(newSnakeSpeed: Long) {
    _uiState = _uiState.copy(snakeSpeed = newSnakeSpeed)
  }

  fun setLastMoveTime(newLastMoveTime: Long) {
    _uiState = _uiState.copy(lastMoveTime = newLastMoveTime)
  }

  fun restart() {
    setSnake(listOf(Cell(5, 5)))
    setCurrentDirection(Direction.RIGHT)
    setFood(generateFood(uiState.snake))
  }
}