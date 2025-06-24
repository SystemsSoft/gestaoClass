package org.gestao.view.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.dashboard.dashBoardScreen
import org.gestao.view.closeAccessScreen
import org.gestao.view.closeClassScreen
import org.gestao.view.closeUploadScreen
import org.gestao.view.menu.dashScreen

@Composable
fun dashNavigation() {
    AnimatedVisibility (
        visible = dashScreen.collectAsState().value,
        enter = slideInHorizontally(
            initialOffsetX = { it },
            animationSpec = tween(durationMillis = 1000)
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { it },
            animationSpec = tween(durationMillis = 1000)
        )
    ) {
        if(dashScreen.value) {
            closeClassScreen()
            closeUploadScreen()
            closeAccessScreen()
        }
        dashBoardScreen()
    }
}