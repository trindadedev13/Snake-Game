package dev.trindadedev.snakegame

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/** Snake State */
@Immutable
data class SnakeState(
  val body: List<SnakeCell> = listOf(SnakeCell(5, 5)),
  val head: SnakeCell = SnakeCell(5, 5),
  val direction: Direction = Direction.RIGHT,
  val foodEaten: Int = 0,
  val growing: Boolean = false,
)

/** Snake Body Cell in Grid */
@Immutable data class SnakeCell(override val x: Int, override val y: Int) : GridItem(x, y)

class Snake {
  private var _state by mutableStateOf(SnakeState())
  val state: SnakeState
    get() = _state

  /** Moves the snake in the current direction. */
  fun moveSnake() {
    val newHead =
      when (_state.direction) {
        Direction.UP -> SnakeCell(_state.head.x, _state.head.y - 1)
        Direction.DOWN -> SnakeCell(_state.head.x, _state.head.y + 1)
        Direction.LEFT -> SnakeCell(_state.head.x - 1, _state.head.y)
        Direction.RIGHT -> SnakeCell(_state.head.x + 1, _state.head.y)
      }
    setHead(newHead)
  }

  /** Sets the snake's direction. */
  fun setDirection(newDirection: Direction) {
    _state = _state.copy(direction = newDirection)
  }

  /** Marks the snake to grow on the next move. */
  fun growSnake() {
    _state = _state.copy(growing = true, foodEaten = state.foodEaten + 1)
  }

  /** Resets the snake state. */
  fun resetSnakeState() {
    _state = SnakeState()
  }

  /** Updates the head and adjusts the body accordingly. */
  private fun setHead(newHead: SnakeCell) {
    val mutableBody = _state.body.toMutableList()
    mutableBody.add(0, newHead)

    if (!_state.growing) {
      mutableBody.removeAt(mutableBody.size - 1)
    } else {
      _state = _state.copy(growing = false)
    }

    _state = _state.copy(head = newHead, body = mutableBody)
  }
}
