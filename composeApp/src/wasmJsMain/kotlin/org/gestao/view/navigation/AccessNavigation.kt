package org.gestao.view.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.flow.MutableStateFlow
import org.gestao.view.access.accessScreen
import org.gestao.view.access.registrationScreen
import org.gestao.view.access.editAccessScreen
import org.gestao.view.access.editSelectedAccess
import org.gestao.view.menu.accessScreen

var openAccessRegistration = MutableStateFlow(false)
var openEditAccess = MutableStateFlow(false)
var openEditItemAccess = MutableStateFlow(false)

@Composable
fun accessNavigation() {
    AnimatedVisibility (
        visible = accessScreen.collectAsState().value,
        enter = slideInHorizontally(
            initialOffsetX = { it },
            animationSpec = tween(durationMillis = 1000)
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { it },
            animationSpec = tween(durationMillis = 1000)
        )
    ) {
        if(accessScreen.value) {
            closeClassScreen()
            closeUploadScreen()
        }

        openAccessRegistration.value = false
        openEditAccess.value = false
        accessScreen()
    }
}

@Composable
fun accessRegistrationNavigation() {
    AnimatedVisibility (
        visible = openAccessRegistration.collectAsState().value,
        enter = slideInHorizontally(
            initialOffsetX = { it },
            animationSpec = tween(durationMillis = 1000)
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { it },
            animationSpec = tween(durationMillis = 1000)
        )
    ) {
        if(openAccessRegistration.value) {
            openEditAccess.value = false
            openEditItemAccess.value = false
        }
        registrationScreen()
    }
}

@Composable
fun editAccessNavigation() {
    AnimatedVisibility (
        visible = openEditAccess.collectAsState().value,
        enter = slideInHorizontally(
            initialOffsetX = { it },
            animationSpec = tween(durationMillis = 1000)
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { it },
            animationSpec = tween(durationMillis = 1000)
        )
    ) {
        if(openEditAccess.value) {
            openAccessRegistration.value = false
            openEditItemAccess.value = false
        }

        editAccessScreen()
    }
}

@Composable
fun editItemAccessNavigation() {
    AnimatedVisibility (
        visible = openEditItemAccess.collectAsState().value,
        enter = slideInHorizontally(
            initialOffsetX = { it },
            animationSpec = tween(durationMillis = 1000)
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { it },
            animationSpec = tween(durationMillis = 1000)
        )
    ) {
        if(openEditItemAccess.value) {
            openEditAccess.value = false
            openAccessRegistration.value = false
        }

        editSelectedAccess()
    }
}
