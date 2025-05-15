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
import org.gestao.view.editRegistrationIcon
import org.gestao.view.navigation.openUploadRegistration
import org.gestao.view.navigation.openEditUpload
import org.gestao.view.navigation.uploadRegistrationNavigation
import org.gestao.view.navigation.editItemUploadNavigation
import org.gestao.view.navigation.editUploadNavigation
import org.gestao.view.newRegistrationIcon
import org.gestao.view.showFilterIcon

@Composable
fun uploadFilesScreen() {
    Card(
        modifier = Modifier
            .width(1200.dp)
            .padding(start = 150.dp,top = 100.dp,end = 100.dp, bottom = 50.dp)
    ) {
        Column(
            modifier = Modifier.background(backgroundCard)
        ) {
            Row (
                modifier = Modifier
                    .width(400.dp)
                    .background(backgroundCard, RoundedCornerShape(topEnd = 8.dp, bottomEnd = 50.dp))
            ){
                newRegistrationIcon(onClick = { openUploadRegistration.value = !openUploadRegistration.value })
                editRegistrationIcon(onClick = { openEditUpload.value = !openEditUpload.value })
                showFilterIcon()
            }
            uploadRegistrationNavigation()
            editUploadNavigation()
            editItemUploadNavigation()
        }
    }
}