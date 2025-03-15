package dev.trindadedev.snakegame

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import dev.trindadedev.snakegame.ui.theme.SnakeTheme

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
  CanvasBasedWindow(canvasElementId = "ComposeTarget") { SnakeTheme { SnakeGameApp() } }
}
