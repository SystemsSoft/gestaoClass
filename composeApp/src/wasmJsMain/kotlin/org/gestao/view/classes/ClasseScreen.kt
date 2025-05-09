package org.gestao.view.classes


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
import org.gestao.view.navigation.abrirCadastroClasse
import org.gestao.view.navigation.abrirEditarClasse
import org.gestao.view.navigation.cadastroClasseNavigation
import org.gestao.view.navigation.editarClasseNavigation
import org.gestao.view.navigation.editarItemClasseNavigation

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
                novoCadastroClasseIcon(onClick = { abrirCadastroClasse.value = !abrirCadastroClasse.value })
                editarCadastroClasseIcon(onClick = { abrirEditarClasse.value = !abrirEditarClasse.value })
            }
            cadastroClasseNavigation()
            editarClasseNavigation()
            editarItemClasseNavigation()
        }
    }
}

