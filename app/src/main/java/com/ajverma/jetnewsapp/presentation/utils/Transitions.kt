package com.ajverma.jetnewsapp.presentation.utils

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntOffset
import kotlin.math.roundToInt

@ExperimentalAnimationApi
fun enterTransition() = slideInHorizontally(
    initialOffsetX = { 300 },
    animationSpec = tween(300)
) + fadeIn(animationSpec = tween(300))

@ExperimentalAnimationApi
fun exitTransition() = slideOutHorizontally(
    targetOffsetX = { -300 },
    animationSpec = tween(300)
) + fadeOut(animationSpec = tween(300))

@ExperimentalAnimationApi
fun popEnterTransition() = slideInHorizontally(
    initialOffsetX = { -300 },
    animationSpec = tween(300)
) + fadeIn(animationSpec = tween(300))

@ExperimentalAnimationApi
fun popExitTransition() = slideOutHorizontally(
    targetOffsetX = { 300 },
    animationSpec = tween(300)
) + fadeOut(animationSpec = tween(300)) 