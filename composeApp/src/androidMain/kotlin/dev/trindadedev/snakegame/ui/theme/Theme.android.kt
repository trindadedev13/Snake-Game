package dev.trindadedev.snakegame.ui.theme

import android.app.Activity
import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
internal actual fun SnakeTheme(content: @Composable () -> Unit) {
  val view = LocalView.current

  val darkTheme = rememberIsSystemInDarkTheme()
  val highContrastDarkTheme = rememberIsHightContrastTheme()

  if (!view.isInEditMode) {
    SideEffect {
      val window = (view.context as Activity).window
      window.statusBarColor = Color.Transparent.toArgb()
      window.navigationBarColor = Color.Transparent.toArgb()

      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        window.isStatusBarContrastEnforced = false
        window.isNavigationBarContrastEnforced = false
      }

      WindowCompat.setDecorFitsSystemWindows(window, false)
      WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
    }
  }

  val context = LocalContext.current
  val scheme =
    when {
      supportsDynamicTheming() -> {
        when {
          darkTheme && highContrastDarkTheme ->
            dynamicDarkColorScheme(context).copy(background = Color.Black, surface = Color.Black)
          darkTheme -> dynamicDarkColorScheme(context)
          else -> dynamicLightColorScheme(context)
        }
      }
      else -> AppColorScheme()
    }

  MaterialTheme(colorScheme = scheme, typography = Typography(), content = content)
}

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
fun supportsDynamicTheming() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
