package org.gestao.view.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.flow.MutableStateFlow
import org.gestao.view.access.accessScreen
import org.gestao.view.access.cleanAccessFields
import org.gestao.view.access.registrationScreen
import org.gestao.view.access.editAccessScreen
import org.gestao.view.access.editSelectedAccess
import org.gestao.view.menu.accessScreen
import org.gestao.view.classFilterIcon
import org.gestao.view.closeClassScreen
import org.gestao.view.closeDashScreen
import org.gestao.view.closeLiveScreen
import org.gestao.view.closeUploadScreen

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
            closeDashScreen()
            closeLiveScreen()
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
            classFilterIcon.value = false
        }
        cleanAccessFields()
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
            classFilterIcon.value = true
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
            classFilterIcon.value = false
        }

        editSelectedAccess()
    }
}
