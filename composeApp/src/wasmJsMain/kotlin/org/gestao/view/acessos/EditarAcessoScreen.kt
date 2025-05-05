package org.gestao.view.acessos

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import model.Acessos
import model.AcessosDto
import org.gestao.viewmodel.allAcessos

@Composable
fun editarAcessoScreen() {
    val getAllAcessos = remember { mutableStateListOf<AcessosDto>() }

    LaunchedEffect(Unit) {
        getAllAcessos.addAll(allAcessos.value)
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(getAllAcessos) { acesso ->
            AcessoItem(acesso)
        }
    }
}

@Composable
fun AcessoItem(acesso: AcessosDto) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = "Nome: ${acesso.nome}",
            fontSize = 10.sp
        )
        Text(
            text = "Email: ${acesso.email}",
            fontSize = 10.sp
        )
        Text(
            text = "Classe: ${acesso.className}",
            fontSize = 10.sp
        )
        Divider(modifier = Modifier.padding(vertical = 4.dp))
    }
}
