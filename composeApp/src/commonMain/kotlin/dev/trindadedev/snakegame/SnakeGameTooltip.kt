package dev.trindadedev.snakegame

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RichTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun Tooltip(
  text: String,
  content: @Composable () -> Unit
) {
  val hapticFeedback = LocalHapticFeedback.current
  val tooltipState = rememberTooltipState()
  val positionProvider = TooltipDefaults.rememberRichTooltipPositionProvider()

  TooltipBox(
    tooltip = { RichTooltip { Text(text) } },
    modifier = Modifier.combinedClickable(
       onClick = {},
       onLongClick = {
          hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
       }
    ),
    positionProvider = positionProvider,
    state = tooltipState,
    content = content
  )
}