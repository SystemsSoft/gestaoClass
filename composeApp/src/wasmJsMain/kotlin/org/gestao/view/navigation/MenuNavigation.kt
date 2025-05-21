package org.gestao.view.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import org.gestao.view.menu.menuScreen
import org.gestao.view.menu.navigationRail

@Composable
fun menuNavigation() {
    AnimatedVisibility(
        visible = menuScreen.collectAsState().value,
        enter = slideInHorizontally(
            initialOffsetX = { fullWidth -> fullWidth },
            animationSpec = tween(durationMillis = 2000)
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { fullWidth -> -fullWidth },
            animationSpec = tween(durationMillis = 2000)
        )
    ) {
        navigationRail()
    }
}

