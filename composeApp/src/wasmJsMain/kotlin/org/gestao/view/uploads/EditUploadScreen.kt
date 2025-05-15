package org.gestao.view.uploads

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
import androidx.compose.runtime.mutableStateOf
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
import org.gestao.model.UploadDto
import org.gestao.view.dialogClassFilter
import org.gestao.view.isLoadingValidate
import org.gestao.view.navigation.abrirEditarItemUpload
import org.gestao.viewmodel.allUploads
import org.gestao.viewmodel.bindDeleteFile
import org.gestao.viewmodel.bindUpdateFile
import org.gestao.viewmodel.uploadListDto

@Composable
fun editUploadScreen() {
    val getAllUploads = remember { mutableStateListOf<UploadDto>() }
    val selectedClassCode = remember { mutableStateOf<String?>(null) }


    LaunchedEffect(Unit) {
        getAllUploads.addAll(allUploads.value)
    }

    val filteredUploadss = if (selectedClassCode.value != null) {
        getAllUploads.filter { it.classCode == selectedClassCode.value }
    } else {
        getAllUploads
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(filteredUploadss) { upload ->
            uploadsItem(
                upload = upload,
                onClick = { selected ->
                    uploadListDto.value.id = selected.id?:0
                    uploadListDto.value.fileName = selected.fileName
                    uploadListDto.value.classCode = selected.classCode
                    uploadListDto.value.fileType = selected.fileType
                    uploadListDto.value.fileCode = selected.fileCode
                }
            )
        }
    }
    dialogClassFilter(
        getAllUploads,
        getItemName = { "" },
        getItemCode = { it.classCode },
        selectedClassCode
    )

}

@Composable
fun uploadsItem(upload: UploadDto, onClick: (UploadDto) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick(upload)
                abrirEditarItemUpload.value = true
            }
            .padding(8.dp)
    ) {
        Text(
            text = "Título: ${upload.fileName}",
            fontSize = 10.sp
        )
        Text(
            text = "Tipo arquivo: ${upload.fileType}",
            fontSize = 10.sp
        )
        Divider(modifier = Modifier.padding(vertical = 4.dp))
    }
}

@Composable
fun editSelectedUpload() {
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
                value = uploadListDto.value.fileName,
                onValueChange = { uploadListDto.value.fileName = it },

                label = {
                    Text(
                        "Título",
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
                value = uploadListDto.value.fileType,
                onValueChange = { uploadListDto.value.fileType = it },
                label = {
                    Text(
                        "Tipo arquivo",
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
                onClick = { bindUpdateFile() },
                modifier = Modifier
                    .padding(40.dp)
                    .align(Alignment.CenterVertically),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Atualizar arquivo", color = Color.Black)
            }

            Button(
                onClick = { bindDeleteFile() },
                modifier = Modifier
                    .padding(40.dp)
                    .align(Alignment.CenterVertically),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Excluir arquivo", color = Color.Black)
            }
        }
        isLoadingValidate()
    }
}