package dev.trindadedev.snakegame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import dev.trindadedev.snakegame.ui.theme.SnakeTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      enableEdgeToEdge()
      SnakeTheme { Surface { Main() } }
    }
  }
}
