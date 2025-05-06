package org.gestao.view.acessos

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import gestaoweb.bbf.com.util.Theme.backgroundCard
import org.gestao.view.navigation.abrirCadastroAcesso
import org.gestao.view.navigation.abrirEditarAcesso
import org.gestao.view.navigation.cadastroAcessoNavigation
import org.gestao.view.navigation.editarAcessoNavigation
import org.gestao.view.navigation.editarItemAcessoNavigation

@Composable
fun acessoScreen() {
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
                novoCadastroIcon(onClick = { abrirCadastroAcesso.value = !abrirCadastroAcesso.value })
                editarCadastroIcon(onClick = { abrirEditarAcesso.value = !abrirEditarAcesso.value })
            }
            cadastroAcessoNavigation()
            editarAcessoNavigation()
            editarItemAcessoNavigation()
        }
    }
}

