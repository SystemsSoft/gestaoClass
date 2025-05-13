package org.gestao.view.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.flow.MutableStateFlow
import org.gestao.view.menu.uploadScreen
import org.gestao.view.uploads.cadastroUploadScreen
import org.gestao.view.uploads.editarUploadScreen
import org.gestao.view.uploads.editarUploadSelecionado
import org.gestao.view.uploads.uploadFilesScreen

var abrirCadastroUpload = MutableStateFlow(false)
var abrirEditarUpload = MutableStateFlow(false)
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
            closeAcessoScreen()
            closeClasseScreen()
        }
        abrirCadastroUpload.value = false
        abrirEditarUpload.value = false
        uploadFilesScreen()
    }
}

@Composable
fun cadastroUploadNavigation() {
    AnimatedVisibility (
        visible = abrirCadastroUpload.collectAsState().value,
        enter = slideInHorizontally(
            initialOffsetX = { it },
            animationSpec = tween(durationMillis = 1000)
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { it },
            animationSpec = tween(durationMillis = 1000)
        )
    ) {
        if(abrirCadastroUpload.value) {
            abrirEditarItemUpload.value = false
            abrirEditarUpload.value = false
        }
        cadastroUploadScreen()
    }
}


@Composable
fun editarUploadNavigation() {
    AnimatedVisibility (
        visible = abrirEditarUpload.collectAsState().value,
        enter = slideInHorizontally(
            initialOffsetX = { it },
            animationSpec = tween(durationMillis = 1000)
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { it },
            animationSpec = tween(durationMillis = 1000)
        )
    ) {
        if(abrirEditarUpload.value) {
            abrirCadastroUpload.value = false
            abrirEditarItemUpload.value = false
        }

        editarUploadScreen()
    }
}
@Composable
fun editarItemUploadNavigation() {
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
            abrirEditarUpload.value = false
            abrirCadastroUpload.value = false
        }
        editarUploadSelecionado()
    }
}