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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import gestaoclass.composeapp.generated.resources.Res
import gestaoclass.composeapp.generated.resources.ic_editar
import gestaoclass.composeapp.generated.resources.ic_novo
import gestaoweb.bbf.com.util.Theme.colorIconClient
import gestaoweb.bbf.com.util.Theme.darkBlueColor
import gestaoweb.bbf.com.util.Theme.fontDefault
import gestaoweb.bbf.com.util.Theme.heightField
import org.gestao.model.ClassesDto
import org.gestao.viewmodel.acessosDto
import org.gestao.viewmodel.allClasses
import org.gestao.viewmodel.bindCadastroAcesso
import org.gestao.viewmodel.className
import org.gestao.viewmodel.codSelected
import org.gestao.viewmodel.requestStatus
import org.gestao.viewmodel.showDialogRetornoCadastro
import org.jetbrains.compose.resources.painterResource


@Composable
fun cadastroScreen() {
    val focusRequesterNome = remember { FocusRequester() }
    val focusRequesterSenha = remember { FocusRequester() }
    val focusRequesterEmail = remember { FocusRequester() }

    val allClassesList = remember { mutableStateListOf<ClassesDto>() }
    val errorMessage by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    LaunchedEffect(Unit){
        allClassesList.addAll(allClasses.value)
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
                    .onKeyEvent{ keyEvent ->
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
                    .onKeyEvent{ keyEvent ->
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
                text =  if (className.value.trim().isEmpty()) {
                    "Selecionar classe"
                } else {
                    className.value
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
            allClassesList.forEach { item ->
                DropdownMenuItem(
                    onClick = {
                        className.value = item.className
                        codSelected.value = item.codClass
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

        Button(
            onClick = { bindCadastroAcesso() },
            modifier = Modifier
                .padding(40.dp)
                .align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Cadastrar", color = Color.Black)
        }
    }
}

@Composable
fun novoCadastroIcon(onClick: () -> Unit) {
    Row(
        modifier =
            Modifier.padding(8.dp)
    ) {
        IconButton(onClick = onClick) {
            Icon(
                painterResource(Res.drawable.ic_novo),
                contentDescription = "NOVO",
                tint = colorIconClient,
            )
        }

        Text(
            text = "NOVO",
            color = Color.Black,
            modifier = Modifier.padding(
                top = 20.dp
            ),
            style = TextStyle(fontSize = fontDefault)
        )
    }
}

@Composable
fun editarCadastroIcon(onClick: () -> Unit) {
    Row(
        modifier =
            Modifier.padding(8.dp)
    ) {
        IconButton(onClick = onClick) {
            Icon(
                painterResource(Res.drawable.ic_editar),
                contentDescription = "EDITAR",
                tint = colorIconClient,
            )
        }

        Text(
            text = "EDITAR",
            color = Color.Black,
            modifier = Modifier.padding(
                top = 20.dp
            ),
            style = TextStyle(fontSize = fontDefault)
        )
    }
}

@Composable
private fun observarRetornoStatus() {
    when (requestStatus.collectAsState().value) {
        200 -> showDialogRetornoCadastro.value = true
        400 -> showDialogRetornoCadastro.value = true
    }
}