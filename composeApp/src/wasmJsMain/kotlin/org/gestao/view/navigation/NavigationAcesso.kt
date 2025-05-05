package org.gestao.view.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.flow.MutableStateFlow
import org.gestao.view.acessos.acessoScreen
import org.gestao.view.acessos.cadastroScreen
import org.gestao.view.acessos.editarAcessoScreen
import org.gestao.view.menu.acessosScreen


var abrirCadastroAcesso = MutableStateFlow(false)
var abrirEditarAcesso = MutableStateFlow(false)

@Composable
fun acessoNavigation() {
    AnimatedVisibility (
        visible = acessosScreen.collectAsState().value,
        enter = slideInHorizontally(
            initialOffsetX = { it },
            animationSpec = tween(durationMillis = 1000)
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { it },
            animationSpec = tween(durationMillis = 1000)
        )
    ) {
        abrirCadastroAcesso.value = false
        abrirEditarAcesso.value = false
        acessoScreen()
    }
}

@Composable
fun cadastroAcessoNavigation() {
    AnimatedVisibility (
        visible = abrirCadastroAcesso.collectAsState().value,
        enter = slideInHorizontally(
            initialOffsetX = { it },
            animationSpec = tween(durationMillis = 1000)
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { it },
            animationSpec = tween(durationMillis = 1000)
        )
    ) {
        if(abrirCadastroAcesso.value) {
            abrirEditarAcesso.value = false
        }
        cadastroScreen()
    }
}

@Composable
fun editarAcessoNavigation() {
    AnimatedVisibility (
        visible = abrirEditarAcesso.collectAsState().value,
        enter = slideInHorizontally(
            initialOffsetX = { it },
            animationSpec = tween(durationMillis = 1000)
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { it },
            animationSpec = tween(durationMillis = 1000)
        )
    ) {
        if(abrirEditarAcesso.value) {
            abrirCadastroAcesso.value = false
        }

        editarAcessoScreen()
    }
}
