package com.example.maquillaje.utils

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut

object AnimationUtils {
    val enterTransition: EnterTransition = fadeIn(
        animationSpec = tween(300)
    )

    val exitTransition: ExitTransition = fadeOut(
        animationSpec = tween(300)
    )
}
