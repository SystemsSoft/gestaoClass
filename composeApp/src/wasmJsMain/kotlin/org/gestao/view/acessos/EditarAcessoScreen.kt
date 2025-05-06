package org.gestao.view.acessos

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gestaoweb.bbf.com.util.Theme.darkBlueColor
import gestaoweb.bbf.com.util.Theme.fontDefault
import gestaoweb.bbf.com.util.Theme.heightField
import model.AcessosDto
import org.gestao.model.ClassesList
import org.gestao.networking.fetchAllClasses
import org.gestao.view.navigation.abrirEditarItemAcesso
import org.gestao.viewmodel.acessosDto
import org.gestao.viewmodel.allAcessos
import org.gestao.viewmodel.bindAtualizarAcesso
import org.gestao.viewmodel.bindCadastroAcesso
import org.gestao.viewmodel.bindExcluirAcesso
import org.gestao.viewmodel.classSelected

@Composable
fun editarAcessoScreen() {
    val getAllAcessos = remember { mutableStateListOf<AcessosDto>() }

    LaunchedEffect(Unit) {
        getAllAcessos.addAll(allAcessos.value)
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(getAllAcessos) { acesso ->
            AcessoItem(
                acesso = acesso,
                onClick = {
                selected ->
                acessosDto.value.id = selected.id.toString()
                acessosDto.value.className = selected.className
                acessosDto.value.codClass = selected.codClass
                acessosDto.value.email = selected.email
                acessosDto.value.nome = selected.nome
                acessosDto.value.senha = selected.senha
            })
        }
    }
}

@Composable
fun AcessoItem(acesso: AcessosDto, onClick: (AcessosDto) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick(acesso)
                abrirEditarItemAcesso.value = true
            }
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

@Composable
fun editarAcessoSelecionado() {
    val focusRequesterNome = remember { FocusRequester() }
    val focusRequesterSenha = remember { FocusRequester() }
    val focusRequesterEmail = remember { FocusRequester() }

    val allClasses = remember { mutableStateListOf<ClassesList>() }
    val errorMessage by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    LaunchedEffect(Unit){
        allClasses.addAll(fetchAllClasses())
    }

    Column(
        Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .padding(start = 4.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            OutlinedTextField(
                value = acessosDto.value.nome,
                onValueChange = { acessosDto.value.nome = it },
                label = { Text("Nome", style = TextStyle(fontSize = fontDefault)) },
                textStyle = TextStyle(fontSize = fontDefault),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.height(heightField)
                    .focusRequester(focusRequesterNome)
                    .onKeyEvent { keyEvent ->
                        when (keyEvent.key) {
                            Key.Tab -> {
                                focusRequesterSenha.requestFocus()
                                true
                            }

                            else -> false
                        }
                    },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = darkBlueColor,
                    focusedLabelColor = darkBlueColor,
                    cursorColor = Color.Black,
                    textColor = Color.Black
                )
            )

            OutlinedTextField(
                value = acessosDto.value.senha,
                onValueChange = { acessosDto.value.senha = it },
                label = { Text("Senha", style = TextStyle(fontSize = fontDefault)) },
                textStyle = TextStyle(fontSize = fontDefault),
                modifier = Modifier
                    .padding(start = 4.dp)
                    .height(heightField)
                    .focusRequester(focusRequesterSenha)
                    .onKeyEvent { keyEvent ->
                        when (keyEvent.key) {
                            Key.Tab -> {
                                focusRequesterEmail.requestFocus()
                                true
                            }

                            else -> false
                        }
                    },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = darkBlueColor,
                    focusedLabelColor = darkBlueColor,
                    cursorColor = Color.Black,
                    textColor = Color.Black
                )
            )

            OutlinedTextField(
                value = acessosDto.value.email,
                onValueChange = { acessosDto.value.email = it },
                label = { Text("Email", style = TextStyle(fontSize = fontDefault)) },
                textStyle = TextStyle(fontSize = fontDefault),
                modifier = Modifier
                    .padding(start = 4.dp)
                    .height(heightField)
                    .focusRequester(focusRequesterEmail),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = darkBlueColor,
                    focusedLabelColor = darkBlueColor,
                    cursorColor = Color.Black,
                    textColor = Color.Black
                )
            )
        }

        Box(
            modifier = Modifier
                .width(200.dp)
                .padding(top = 10.dp)
                .align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(8.dp))
                .clickable {
                    expanded = true
                }
                .border(width = 1.dp, color = Color.Gray.copy(alpha = 0.5f), shape = RoundedCornerShape(8.dp))

        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text = if (acessosDto.value.className.trim().isEmpty()) {
                    "Selecionar classe"
                } else {
                    acessosDto.value.className
                },
                style = TextStyle(
                    fontSize = 12.sp
                ),
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()

        ) {
            allClasses.forEach { item ->
                DropdownMenuItem(
                    onClick = {
                        acessosDto.value.className = item.className
                        acessosDto.value.codClass = item.codClass
                        expanded = false
                    }
                ) {
                    Text(
                        text = "Classe Nome: ${item.className}",
                        modifier = Modifier.padding(8.dp)
                    )
                    Text(
                        text = "Classe Codigo: ${item.codClass}",
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }

        Row {
            Button(
                onClick = { bindAtualizarAcesso() },
                modifier = Modifier
                    .padding(40.dp)
                    .align(Alignment.CenterVertically),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Atualizar acesso", color = Color.Black)
            }

            Button(
                onClick = { bindExcluirAcesso() },
                modifier = Modifier
                    .padding(40.dp)
                    .align(Alignment.CenterVertically),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Excluir Acesso", color = Color.Black)
            }
        }
    }
}
