package org.gestao.view.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import org.gestao.view.Acessos.abrirEditar
import org.gestao.view.Acessos.editarAcessoScreen


@Composable
fun navigateToEditarCliente() {
    AnimatedVisibility (
        visible = abrirEditar.collectAsState().value,
        enter = slideInHorizontally(
            initialOffsetX = { it },
            animationSpec = tween(durationMillis = 1000)
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { it },
            animationSpec = tween(durationMillis = 1000)
        )
    ) {
        editarAcessoScreen()
    }
}
