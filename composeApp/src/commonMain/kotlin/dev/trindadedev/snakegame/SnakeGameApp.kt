package dev.trindadedev.snakegame

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.trindadedev.snakegame.time.epochMilliseconds
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.stringResource
import snake.composeapp.generated.resources.Res
import snake.composeapp.generated.resources.str_pause
import snake.composeapp.generated.resources.str_play

@Composable
fun SnakeGameApp(viewModel: SnakeViewModel = viewModel { SnakeViewModel() }) {
  val uiState = viewModel.uiState
  val lifecycleOwner = LocalLifecycleOwner.current
  DisposableEffect(lifecycleOwner) {
    val observer = LifecycleEventObserver { _, event ->
      if (event == Lifecycle.Event.ON_PAUSE) {
        viewModel.setIsPaused(true)
      }
    }
    lifecycleOwner.lifecycle.addObserver(observer)
    onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
  }
  LaunchedEffect(uiState) {
    while (true) {
      delay(uiState.gameSpeed)
      if (!uiState.isPaused) {
        if (epochMilliseconds - uiState.lastMoveTime >= uiState.snakeSpeed) {
          uiState.snake.moveSnake()
          viewModel.setLastMoveTime(epochMilliseconds)
        }
        if (
          uiState.snake.state.head.x == uiState.food.x &&
            uiState.snake.state.head.y == uiState.food.y
        ) {
          viewModel.setFood(generateFood())
          uiState.snake.growSnake()
        }
      }
    }
  }
  Box(modifier = Modifier.fillMaxSize()) {
    Column(
      modifier = Modifier.fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.Center,
    ) {
      SnakeGameInfo(
        modifier = Modifier.fillMaxWidth(),
        foodEaten = uiState.snake.state.foodEaten,
        snake = uiState.snake,
      )
      SnakeGameGrid(snake = uiState.snake, food = uiState.food)
      Spacer(modifier = Modifier.height(16.dp))
      SnakeGameDirectionButtons(
        enabled = !uiState.isPaused,
        onClick = {
          if (!uiState.snake.state.direction.isOpposite(it)) viewModel.setCurrentDirection(it)
        },
        centerSlot = {
          SnakeGameDirectionButton(
            name =
              stringResource(
                resource = if (uiState.isPaused) Res.string.str_play else Res.string.str_pause
              ),
            icon =
              if (uiState.isPaused) SnakeGameTokens.DirectionButtons.Icons.Play
              else SnakeGameTokens.DirectionButtons.Icons.Pause,
            onClick = { viewModel.setIsPaused(!uiState.isPaused) },
          )
        },
      )
      Button(
        onClick = { uiState.snake.resetSnakeState() }
      ) {
        Text(text = "Restart")
      }
    }
  }
}
