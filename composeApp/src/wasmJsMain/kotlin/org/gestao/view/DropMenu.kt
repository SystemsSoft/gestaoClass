package org.gestao.view

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.gestao.model.ClassesDto
import org.gestao.util.DefaultFileTypes.typeFiles
import org.gestao.viewmodel.allClasses
import org.gestao.viewmodel.classNameSelected
import org.gestao.viewmodel.codSelected
import org.gestao.viewmodel.uploadDto

@Composable
fun classSelector() {
    val allClassesList = remember { mutableStateListOf<ClassesDto>() }
    var expanded by remember { mutableStateOf(false) }

    classNameSelected.collectAsState().value.let { className ->
    LaunchedEffect(Unit){
        allClassesList.addAll(allClasses.value)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
            Box(
                modifier = Modifier
                    .width(200.dp)
                    .padding(top = 10.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable {
                        expanded = true
                    }
                    .border(width = 1.dp, color = Color.Gray.copy(alpha = 0.5f), shape = RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = if (className.trim().isEmpty()) {
                        "Selecionar classe"
                    } else {
                        className
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
                            classNameSelected.value = item.className
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
        }
    }
}

@Composable
fun fileTypesSelector() {
    var expanded by remember { mutableStateOf(false) }
    uploadDto.collectAsState().value.tipoFile.let {  fileType ->

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {

            Box(
                modifier = Modifier
                    .width(200.dp)
                    .padding(top = 10.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .clickable {
                        expanded = true
                    }
                    .border(width = 1.dp, color = Color.Gray.copy(alpha = 0.5f), shape = RoundedCornerShape(8.dp)),
                Alignment.Center

            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = if (fileType.trim().isEmpty()) {
                        "Selecionar tipo arquivo"
                    } else {
                        fileType
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
                typeFiles.forEach { item ->
                    DropdownMenuItem(
                        onClick = {
                            uploadDto.value.tipoFile = item
                            expanded = false
                        }
                    ) {
                        Text(
                            text = "Tipo arquivo: $item",
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}
