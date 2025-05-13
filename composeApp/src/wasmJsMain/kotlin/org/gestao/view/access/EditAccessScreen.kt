package org.gestao.view.access

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import model.AccessDto
import org.gestao.model.ClassDto
import org.gestao.view.isLoadingValidate
import org.gestao.view.navigation.openEditItemAccess
import org.gestao.viewmodel.accessListDto
import org.gestao.viewmodel.allAccesses
import org.gestao.viewmodel.allClasses
import org.gestao.viewmodel.bindUpdateAccess
import org.gestao.viewmodel.bindDeleteAccess

@Composable
fun editAccessScreen() {
    val getAllAccesses = remember { mutableStateListOf<AccessDto>() }

    LaunchedEffect(Unit) {
        getAllAccesses.addAll(allAccesses.value)
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(getAllAccesses) { acesso ->
            AccessItem(
                access = acesso,
                onClick = {
                selected ->
                accessListDto.value.id = selected.id.toString()
                accessListDto.value.className = selected.className
                accessListDto.value.classCode = selected.classCode
                accessListDto.value.email = selected.email
                accessListDto.value.name = selected.name
                accessListDto.value.password = selected.password
            })
        }
    }
}

@Composable
fun AccessItem(access: AccessDto, onClick: (AccessDto) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick(access)
                openEditItemAccess.value = true
            }
            .padding(8.dp)
    ) {
        Text(
            text = "Nome: ${access.name}",
            fontSize = 10.sp
        )
        Text(
            text = "Email: ${access.email}",
            fontSize = 10.sp
        )
        Text(
            text = "Classe: ${access.className}",
            fontSize = 10.sp
        )
        Divider(modifier = Modifier.padding(vertical = 4.dp))
    }
}

@Composable
fun editSelectedAccess() {
    val focusRequesterNome = remember { FocusRequester() }
    val focusRequesterSenha = remember { FocusRequester() }
    val focusRequesterEmail = remember { FocusRequester() }

    val allClassesList = remember { mutableStateListOf<ClassDto>() }
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
                value = accessListDto.value.name,
                onValueChange = { accessListDto.value.name = it },
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
                value = accessListDto.value.password,
                onValueChange = { accessListDto.value.password = it },
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
                value = accessListDto.value.email,
                onValueChange = { accessListDto.value.email = it },
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
                text = if (accessListDto.value.className.trim().isEmpty()) {
                    "Selecionar classe"
                } else {
                    accessListDto.value.className
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
                        accessListDto.value.className = item.className
                        accessListDto.value.classCode = item.classCode
                        expanded = false
                    }
                ) {
                    Text(
                        text = "Classe Nome: ${item.className}",
                        modifier = Modifier.padding(8.dp)
                    )
                    Text(
                        text = "Classe Codigo: ${item.classCode}",
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Button(
                onClick = { bindUpdateAccess() },
                modifier = Modifier
                    .padding(40.dp)
                    .align(Alignment.CenterVertically),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Atualizar acesso", color = Color.Black)
            }

            Button(
                onClick = { bindDeleteAccess() },
                modifier = Modifier
                    .padding(40.dp)
                    .align(Alignment.CenterVertically),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Excluir Acesso", color = Color.Black)
            }
        }
        isLoadingValidate()
    }
}
