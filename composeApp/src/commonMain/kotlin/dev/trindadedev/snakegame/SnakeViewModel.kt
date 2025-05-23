package dev.trindadedev.snakegame

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SnakeViewModel : ViewModel() {
  private var _uiState by mutableStateOf(SnakeUIState())
  val uiState: SnakeUIState
    get() = _uiState

  fun setCurrentDirection(newCurrentDirection: Direction) {
    uiState.snake.setDirection(newCurrentDirection)
  }

  fun setFood(newFood: Food) {
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

  fun setIsPaused(newIsPaused: Boolean) {
    if (_uiState.isPaused == newIsPaused) return
    _uiState = _uiState.copy(isPaused = newIsPaused)
  }

  fun restartGame() {
    _uiState.snake.resetSnakeState()
    setFood(generateFood())
  }
}
