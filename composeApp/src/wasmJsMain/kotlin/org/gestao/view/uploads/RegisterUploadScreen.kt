package org.gestao.view.uploads

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import gestaoweb.bbf.com.util.Theme.darkBlueColor
import gestaoweb.bbf.com.util.Theme.fontDefault
import gestaoweb.bbf.com.util.Theme.heightField
import org.gestao.util.selectFile
import org.gestao.view.isLoadingValidate
import org.gestao.view.classSelector
import org.gestao.view.fileTypesSelector
import org.gestao.viewmodel.bindUploadRegistration
import org.gestao.viewmodel.uploadListDto

@Composable
fun uploadRegistrationScreen() {

    Column(
        Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .padding(start = 4.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            OutlinedTextField(
                value = uploadListDto.value.fileName,
                onValueChange = { uploadListDto.value.fileName = it },
                label = { Text("Nome", style = TextStyle(fontSize = fontDefault)) },
                textStyle = TextStyle(fontSize = fontDefault),
                modifier = Modifier.height(heightField),

                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = darkBlueColor,
                    focusedLabelColor = darkBlueColor,
                    cursorColor = Color.Black,
                    textColor = Color.Black
                )
            )
        }

        classSelector()
        fileTypesSelector()
        isLoadingValidate()

        Button(
            onClick = { selectFile() },
            modifier = Modifier
                .padding(40.dp)
                .align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Selecionar File", color = Color.Black)
        }

        Button(
            onClick = { bindUploadRegistration() },
            modifier = Modifier
                .padding(40.dp)
                .align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Cadastrar", color = Color.Black)
        }
    }
}
