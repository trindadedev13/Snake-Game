package dev.trindadedev.snakegame.time

import kotlinx.datetime.Clock

val epochMilliseconds: Long
  get() = Clock.System.now().toEpochMilliseconds()