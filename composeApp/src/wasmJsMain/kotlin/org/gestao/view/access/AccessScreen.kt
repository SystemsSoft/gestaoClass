package org.gestao.view.access


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import gestaoweb.bbf.com.util.Theme.backgroundCard
import org.gestao.view.editRegistrationIcon
import org.gestao.view.navigation.openAccessRegistration
import org.gestao.view.navigation.openEditAccess
import org.gestao.view.navigation.accessRegistrationNavigation
import org.gestao.view.navigation.editAccessNavigation
import org.gestao.view.navigation.editItemAccessNavigation
import org.gestao.view.newRegistrationIcon
import org.gestao.view.showFilterIcon

@Composable
fun accessScreen() {
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
                newRegistrationIcon(onClick = { openAccessRegistration.value = !openAccessRegistration.value })
                editRegistrationIcon(onClick = { openEditAccess.value = !openEditAccess.value })
                showFilterIcon()
            }
            accessRegistrationNavigation()
            editAccessNavigation()
            editItemAccessNavigation()
        }
    }
}

