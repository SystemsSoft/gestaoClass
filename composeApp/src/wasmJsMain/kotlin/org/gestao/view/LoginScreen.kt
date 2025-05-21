package org.gestao.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import gestaoclass.composeapp.generated.resources.Res
import gestaoclass.composeapp.generated.resources.ic_logout
import gestaoclass.composeapp.generated.resources.ic_user
import gestaoclass.composeapp.generated.resources.lock
import gestaoclass.composeapp.generated.resources.person
import gestaoclass.composeapp.generated.resources.visibility
import gestaoclass.composeapp.generated.resources.visibility_off
import gestaoweb.bbf.com.util.Theme.darkBlueColor
import gestaoweb.bbf.com.util.Theme.fontDefault
import gestaoweb.bbf.com.util.Theme.gradientBackground
import kotlinx.browser.window
import org.gestao.viewmodel.authenticationFailed
import org.gestao.viewmodel.validateUser
import org.jetbrains.compose.resources.painterResource


@Composable
fun loginScreen() {
    showUpLoginError()
    authenticationFields()
}

@Composable
fun authenticationFields() {
    var user by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    val focusRequesterUsuario = remember { FocusRequester() }
    val focusRequesterSenha = remember { FocusRequester() }
    val focusRequesterLogin = remember { FocusRequester() }

    Row {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Card(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(end = 50.dp)
                    .size(width = 400.dp, height = 400.dp),
                shape = RoundedCornerShape(28.dp)
            ) {
                Column(
                    modifier = Modifier
                        .background(gradientBackground)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                ) {
                    OutlinedTextField(
                        value = user,
                        singleLine = true,
                        onValueChange = { user = it },
                        label = { Text("Usuário") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(focusRequesterUsuario)
                            .onKeyEvent { keyEvent ->
                                if (keyEvent.key == Key.Enter) {
                                    focusRequesterSenha.requestFocus()
                                    true
                                } else {
                                    false
                                }
                            },
                        leadingIcon = {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .padding(8.dp)
                            ) {
                                Icon(
                                    painter = painterResource(Res.drawable.person),
                                    contentDescription = "Usuário",
                                    tint = Color.White
                                )
                            }
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = Color.LightGray,
                            unfocusedLabelColor = Color.LightGray,
                            focusedBorderColor = Color.White,
                            focusedLabelColor = Color.White,
                            cursorColor = Color.White,
                            textColor = Color.White
                        ),
                        shape = RoundedCornerShape(16.dp)
                    )

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Senha") },
                        singleLine = true,
                        modifier = Modifier
                            .focusRequester(focusRequesterSenha)
                            .padding(top = 20.dp)
                            .fillMaxWidth()
                            .padding(bottom = 50.dp)
                            .onKeyEvent { keyEvent ->
                                if (keyEvent.key == Key.Enter) {
                                    focusRequesterLogin.requestFocus()
                                    true
                                } else {
                                    false
                                }
                            },
                        leadingIcon = {
                            Icon(
                                painterResource(Res.drawable.lock),
                                contentDescription = "Senha",
                                tint = Color.White
                            )
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.White,
                            focusedLabelColor = Color.White,
                            unfocusedLabelColor = Color.LightGray,
                            unfocusedBorderColor = Color.LightGray,
                            cursorColor = Color.White,
                            textColor = Color.White
                        ),
                        shape = RoundedCornerShape(12.dp),
                        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                                val visibilityIcon =
                                    if (isPasswordVisible) Res.drawable.visibility else Res.drawable.visibility_off
                                Icon(
                                    painter = painterResource(visibilityIcon),
                                    contentDescription = if (isPasswordVisible) "Ocultar senha" else "Mostrar senha",
                                    tint = Color.White
                                )
                            }
                        }
                    )

                    Button(
                        onClick = { validateUser(user, password) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 35.dp, end = 35.dp)
                            .focusRequester(focusRequesterLogin),

                        shape = RoundedCornerShape(12.dp),
                    ) {
                        Text(text = "Entrar", color = Color.White)
                    }
                }
            }
        }
    }
}

@Composable
fun showUpLoginError() {
    if (authenticationFailed.collectAsState().value) {
        AlertDialog(
            onDismissRequest = { authenticationFailed.value = false },
            title = { Text("Erro de Autenticação") },
            text = { Text(" Verifique usuário/senha ou se possui licença ativa.") },
            confirmButton = {
                TextButton(onClick = {
                    authenticationFailed.value = false
                }) {
                    Text(
                        text = "OK",
                        style = TextStyle(
                            color = Color.White
                        )
                    )
                }
            },
            contentColor = Color.White,
            backgroundColor = darkBlueColor,
        )
    }
}

@Composable
fun fieldLogOut() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        IconButton(
            onClick = {
                window.location.reload()
            },
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            Icon(
                painterResource(Res.drawable.ic_logout),
                contentDescription = "Sair",
                tint = Color.White
            )
        }
        Text(
            modifier = Modifier
                .padding(end = 40.dp, top = 15.dp)
                .align(Alignment.TopEnd),
            text = "",
            color = Color.White,
            style = TextStyle(fontSize = fontDefault)
        )
    }
}