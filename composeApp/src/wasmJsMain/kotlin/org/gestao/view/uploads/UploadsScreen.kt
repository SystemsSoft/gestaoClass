package org.gestao.view.uploads

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
import org.gestao.view.acessos.editarCadastroIcon
import org.gestao.view.acessos.novoCadastroIcon
import org.gestao.view.navigation.abrirCadastroUpload
import org.gestao.view.navigation.abrirEditarUpload
import org.gestao.view.navigation.cadastroUploadNavigation
import org.gestao.view.navigation.editarItemUploadNavigation
import org.gestao.view.navigation.editarUploadNavigation

@Composable
fun uploadFilesScreen() {
    Card(
        modifier = Modifier
            .width(1200.dp)
            .height(600.dp)
            .padding(start = 150.dp,top = 50.dp,end = 50.dp, bottom = 50.dp)
    ) {
        Column(
            modifier = Modifier.background(backgroundCard)
        ) {
            Row (
                modifier = Modifier
                    .width(400.dp)
                    .background(backgroundCard, RoundedCornerShape(topEnd = 8.dp, bottomEnd = 50.dp))
            ){
                novoCadastroIcon(onClick = { abrirCadastroUpload.value = !abrirCadastroUpload.value })
                editarCadastroIcon(onClick = { abrirEditarUpload.value = !abrirEditarUpload.value })
            }
            cadastroUploadNavigation()
            editarUploadNavigation()
            editarItemUploadNavigation()
        }
    }
}