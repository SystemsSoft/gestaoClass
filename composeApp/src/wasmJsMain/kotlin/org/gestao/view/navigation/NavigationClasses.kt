package org.gestao.view.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.flow.MutableStateFlow
import org.gestao.view.acessos.editarAcessoScreen
import org.gestao.view.acessos.editarAcessoSelecionado
import org.gestao.view.classes.cadastroClasseScreen
import org.gestao.view.classes.classeScreen
import org.gestao.view.classes.editarClasseSelecionado
import org.gestao.view.classes.editarClassesScreen
import org.gestao.view.menu.acessosScreen
import org.gestao.view.menu.classesScreen

var abrirCadastroClasse = MutableStateFlow(false)
var abrirEditarClasse = MutableStateFlow(false)
var abrirEditarItemClasse = MutableStateFlow(false)

@Composable
fun classeNavigation() {
    AnimatedVisibility (
        visible = classesScreen.collectAsState().value,
        enter = slideInHorizontally(
            initialOffsetX = { it },
            animationSpec = tween(durationMillis = 1000)
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { it },
            animationSpec = tween(durationMillis = 1000)
        )
    ) {
        if (classesScreen.value){
           closeAcessoScreen()
        }
        abrirCadastroClasse.value = false
        abrirEditarClasse.value = false
        classeScreen()
    }
}

@Composable
fun cadastroClasseNavigation() {
    AnimatedVisibility (
        visible = abrirCadastroClasse.collectAsState().value,
        enter = slideInHorizontally(
            initialOffsetX = { it },
            animationSpec = tween(durationMillis = 1000)
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { it },
            animationSpec = tween(durationMillis = 1000)
        )
    ) {
        if(abrirCadastroClasse.value) {
            abrirEditarItemClasse.value = false
            abrirEditarClasse.value = false
        }
        cadastroClasseScreen()
    }
}

@Composable
fun editarClasseNavigation() {
    AnimatedVisibility (
        visible = abrirEditarClasse.collectAsState().value,
        enter = slideInHorizontally(
            initialOffsetX = { it },
            animationSpec = tween(durationMillis = 1000)
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { it },
            animationSpec = tween(durationMillis = 1000)
        )
    ) {
        if(abrirEditarClasse.value) {
            abrirCadastroClasse.value = false
            abrirEditarItemClasse.value = false
        }

        editarClassesScreen()
    }
}


@Composable
fun editarItemClasseNavigation() {
    AnimatedVisibility (
        visible = abrirEditarItemClasse.collectAsState().value,
        enter = slideInHorizontally(
            initialOffsetX = { it },
            animationSpec = tween(durationMillis = 1000)
        ),
        exit = slideOutHorizontally(
            targetOffsetX = { it },
            animationSpec = tween(durationMillis = 1000)
        )
    ) {
        if(abrirEditarItemClasse.value) {
            abrirEditarClasse.value = false
            abrirCadastroClasse.value = false
        }
        editarClasseSelecionado()
    }
}