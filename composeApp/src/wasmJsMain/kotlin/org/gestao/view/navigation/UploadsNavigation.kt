package org.gestao.view.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.flow.MutableStateFlow
import org.gestao.view.classFilterIcon
import org.gestao.view.closeAccessScreen
import org.gestao.view.closeClassScreen
import org.gestao.view.closeDashScreen
import org.gestao.view.closeLiveScreen
import org.gestao.view.menu.uploadScreen
import org.gestao.view.uploads.cleanUploadsFields
import org.gestao.view.uploads.uploadRegistrationScreen
import org.gestao.view.uploads.editUploadScreen
import org.gestao.view.uploads.editSelectedUpload
import org.gestao.view.uploads.uploadFilesScreen

var openUploadRegistration = MutableStateFlow(false)
var openEditUpload = MutableStateFlow(false)
var abrirEditarItemUpload = MutableStateFlow(false)


@Composable
fun uploadNavigation() {
    AnimatedVisibility (
        visible = uploadScreen.collectAsState().value,
        enter = slideInHorizontally(
            initialOffsetX = { it },
            animationSpec = tween(durationMillis = 1000)
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { it },
            animationSpec = tween(durationMillis = 1000)
        )
    ) {
        if (uploadScreen.value){
            closeAccessScreen()
            closeClassScreen()
            closeDashScreen()
            closeLiveScreen()
        }
        openUploadRegistration.value = false
        openEditUpload.value = false
        uploadFilesScreen()
    }
}

@Composable
fun uploadRegistrationNavigation() {
    AnimatedVisibility (
        visible = openUploadRegistration.collectAsState().value,
        enter = slideInHorizontally(
            initialOffsetX = { it },
            animationSpec = tween(durationMillis = 1000)
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { it },
            animationSpec = tween(durationMillis = 1000)
        )
    ) {
        if(openUploadRegistration.value) {
            abrirEditarItemUpload.value = false
            openEditUpload.value = false
            classFilterIcon.value = false
        }
        cleanUploadsFields()
        uploadRegistrationScreen()
    }
}


@Composable
fun editUploadNavigation() {
    AnimatedVisibility (
        visible = openEditUpload.collectAsState().value,
        enter = slideInHorizontally(
            initialOffsetX = { it },
            animationSpec = tween(durationMillis = 1000)
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { it },
            animationSpec = tween(durationMillis = 1000)
        )
    ) {
        if(openEditUpload.value) {
            openUploadRegistration.value = false
            abrirEditarItemUpload.value = false
            classFilterIcon.value = true
        }

        editUploadScreen()
    }
}
@Composable
fun editItemUploadNavigation() {
    AnimatedVisibility (
        visible = abrirEditarItemUpload.collectAsState().value,
        enter = slideInHorizontally(
            initialOffsetX = { it },
            animationSpec = tween(durationMillis = 1000)
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { it },
            animationSpec = tween(durationMillis = 1000)
        )
    ) {
        if(abrirEditarItemUpload.value) {
            openEditUpload.value = false
            openUploadRegistration.value = false
            classFilterIcon.value = false
        }
        editSelectedUpload()
    }
}