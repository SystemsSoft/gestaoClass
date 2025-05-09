package org.gestao.view

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.AlertDialog
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import gestaoweb.bbf.com.util.Theme.transparentColor
import kotlinx.coroutines.flow.MutableStateFlow
import org.gestao.loadData

import org.gestao.util.RequestResults.ERROR_MSG
import org.gestao.util.RequestResults.ERROR_TITLE
import org.gestao.util.RequestResults.SUCCESS_MSG
import org.gestao.util.RequestResults.SUCCESS_TITLE
import org.gestao.view.menu.acessosScreen
import org.gestao.view.menu.classesScreen
import org.gestao.view.menu.itemMenuSelected
import org.gestao.view.navigation.abrirEditarAcesso
import org.gestao.view.navigation.abrirEditarClasse
import org.gestao.view.navigation.abrirEditarItemAcesso
import org.gestao.view.navigation.abrirEditarItemClasse
import org.gestao.view.navigation.closeAcessoScreen
import org.gestao.view.navigation.closeClasseScreen
import org.gestao.viewmodel.clearAcessoDTO
import org.gestao.viewmodel.clearClasseDTO
import org.gestao.viewmodel.requestStatus

var showAlert = MutableStateFlow(false)
var isLoading = MutableStateFlow(false)

@Composable
fun observeRequestStatus() {
    requestStatus.collectAsState().value.let {
         when(it) {
             200 -> {
                 isLoading.value = false
                 showAlert.value = true
                 showMensage(SUCCESS_TITLE,SUCCESS_MSG)
                 restartView()
                 loadData()
             }
             0 -> isLoading.value = false
             else -> {
                 isLoading.value = false
                 showAlert.value = true
                 showMensage(ERROR_TITLE,ERROR_MSG)
             }
         }
    }
}

@Composable
fun showMensage(title: String,result: String) {
    if(showAlert.collectAsState().value) {
        AlertDialog(
            onDismissRequest = {
                restartRequest()
                showAlert.value = false },
            title = {
                Text(
                    text = title,
                    style = TextStyle(
                        color = Color.Black
                    )
                )
            },
            text = {
                Text(
                    text = result,
                    style = TextStyle(
                        color = Color.Black
                    )
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                    showAlert.value = false
                        restartRequest()
                    }) {
                    Text(
                        text = "OK",
                        style = TextStyle(
                            color = Color.Black
                        )
                    )
                }
            }
        )
    }
}

@Composable
fun AnimatedLoadingIndicator() {
    val infiniteTransition = rememberInfiniteTransition(label = "loading_transition")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.4f,
        animationSpec = infiniteRepeatable(
            animation = tween(500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(transparentColor),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .scale(scale)
            ) {
                CircularProgressIndicator(
                    strokeWidth = 3.dp,
                    color = Color.Black,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun isLoadingValidate() {
    if(isLoading.collectAsState().value) {
        AnimatedLoadingIndicator()
    }
}

fun restartRequest() {
    requestStatus.value = 0
}

fun restartView() {
    clearAcessoDTO()
    clearClasseDTO()
    closeAcessoScreen()
    closeClasseScreen()

    classesScreen.value = false
    itemMenuSelected.value = 0
}

