package org.gestao.view.classes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gestaoweb.bbf.com.util.Theme.darkBlueColor
import gestaoweb.bbf.com.util.Theme.fontDefault
import gestaoweb.bbf.com.util.Theme.heightField
import org.gestao.model.ClassesDto
import org.gestao.view.navigation.abrirEditarItemClasse
import org.gestao.viewmodel.allClasses
import org.gestao.viewmodel.bindAtualizarClasse
import org.gestao.viewmodel.bindExcluirClasse
import org.gestao.viewmodel.classDto

@Composable
fun editarClassesScreen() {
    val getAllClasses = remember { mutableStateListOf<ClassesDto>() }

    LaunchedEffect(Unit) {
        getAllClasses.addAll(allClasses.value)
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(getAllClasses) { classe ->
            ClasseItem(
                classe = classe,
                onClick = {
                        selected ->
                    classDto.value.id = selected.id.toString()
                    classDto.value.className = selected.className
                    classDto.value.codClass = selected.codClass
                }
            )
        }
    }
}

@Composable
fun ClasseItem(classe: ClassesDto, onClick: (ClassesDto) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick(classe)
                abrirEditarItemClasse.value = true
            }
            .padding(8.dp)
    ) {
        Text(
            text = "Classe Cod: ${classe.codClass}",
            fontSize = 10.sp
        )
        Text(
            text = "Classe Nome: ${classe.className}",
            fontSize = 10.sp
        )
        Divider(modifier = Modifier.padding(vertical = 4.dp))
    }
}

@Composable
fun editarClasseSelecionado() {
    Column(
        Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .padding(start = 4.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            OutlinedTextField(
                value = classDto.value.className,
                onValueChange = { classDto.value.className = it },

                label = {
                    Text(
                        "Nome da classe",
                        style = TextStyle(
                            fontSize = fontDefault
                        )
                    )
                },
                textStyle = TextStyle(fontSize = fontDefault),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.height(heightField),

                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = darkBlueColor,
                    focusedLabelColor = darkBlueColor,
                    cursorColor = Color.Black,
                    textColor = Color.Black
                ),
            )

            OutlinedTextField(
                value = classDto.value.codClass,
                onValueChange = { classDto.value.codClass = it },
                label = {
                    Text(
                        "Código da classe",
                        style = TextStyle(fontSize = fontDefault)
                    )
                },
                textStyle = TextStyle(fontSize = fontDefault),
                modifier = Modifier
                    .padding(start = 4.dp)
                    .height(heightField),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = darkBlueColor,
                    focusedLabelColor = darkBlueColor,
                    cursorColor = Color.Black,
                    textColor = Color.Black
                )
            )
        }


        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Button(
                onClick = { bindAtualizarClasse() },
                modifier = Modifier
                    .padding(40.dp)
                    .align(Alignment.CenterVertically),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Atualizar acesso", color = Color.Black)
            }

            Button(
                onClick = { bindExcluirClasse() },
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