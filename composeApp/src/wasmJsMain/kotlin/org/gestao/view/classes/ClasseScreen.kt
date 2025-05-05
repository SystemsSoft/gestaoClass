package org.gestao.view.classes

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import gestaoweb.bbf.com.util.Theme.backgroundCard
import kotlinx.coroutines.flow.MutableStateFlow
import org.gestao.view.menu.classesScreen

var abrirCadastro = MutableStateFlow(false)
var abrirEditar = MutableStateFlow(false)
var abrirExcluir = MutableStateFlow(false)
var showAlert  = MutableStateFlow (false)

@Composable
fun openClasseScreen() {
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
        classeScreen()
    }
}

@Composable
fun classeScreen() {
    Card(
        modifier = Modifier
            .width(1200.dp)
            .height(800.dp)
            .padding(start = 200.dp,top = 30.dp,end = 40.dp, bottom = 40.dp)
    ) {
        Column(
            modifier = Modifier.background(backgroundCard)
        ) {
            Row (
                modifier = Modifier
                    .width(400.dp)
                    .background(backgroundCard, RoundedCornerShape(topEnd = 8.dp, bottomEnd = 50.dp))
            ){
                novoCadastroClasseIcon(onClick = { abrirCadastro.value = true })
                editarCadastroClasseIcon(onClick = { abrirEditar.value = true })
                excluirCadastroClasseIcon(onClick = { abrirExcluir.value = !abrirExcluir.value })
            }
            cadastroClasseScreen()
        }
    }
}

