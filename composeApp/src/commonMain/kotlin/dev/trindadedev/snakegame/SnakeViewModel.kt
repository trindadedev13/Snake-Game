package dev.trindadedev.snakegame

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class SnakeViewModel: ViewModel() {
  private var _uiState by mutableStateOf(SnakeUIState())
  val uiState: SnakeUIState
    get() = _uiState

  fun setCurrentDirection(currentDirection: Direction) {
    _uiState = _uiState.copy(currentDirection = currentDirection)
  }

  fun setSnake(snake: List<Cell>) {
    _uiState = _uiState.copy(snake = snake)
  }

  fun setFood(food: Cell) {
    _uiState = _uiState.copy(food = food)
  }

  fun setIsGameOver(isGameOver: Boolean) {
    _uiState = _uiState.copy(isGameOver = isGameOver)
  }

  fun setGameId(gameId: Int) {
    _uiState = _uiState.copy(gameId = gameId)
  }

  fun setGameSpeed(gameSpeed: Long) {
    _uiState = _uiState.copy(gameSpeed = gameSpeed)
  }

  fun setSnakeSpeed(snakeSpeed: Long) {
    _uiState = _uiState.copy(snakeSpeed = snakeSpeed)
  }

  fun setLastMoveTime(lastMoveTime: Long) {
    _uiState = _uiState.copy(lastMoveTime = lastMoveTime)
  }
}