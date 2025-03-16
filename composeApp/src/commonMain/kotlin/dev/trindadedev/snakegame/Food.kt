package dev.trindadedev.snakegame

import androidx.compose.runtime.Immutable
import kotlin.random.Random

@Immutable data class Food(override val x: Int, override val y: Int) : GridItem(x, y)

fun generateFood(): Food {
  val emptyGridItems =
    (0 until SnakeGameTokens.GridItem.PerRow).flatMap { x ->
      (0 until SnakeGameTokens.GridItem.PerRow).map { y -> GridItem(x, y) }
    }
  return emptyGridItems[Random.nextInt(emptyGridItems.size)].toFood()
}

private inline fun GridItem.toFood(): Food {
  return Food(x = this.x, y = this.y)
}
