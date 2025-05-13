package org.gestao.view.classes


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
import org.gestao.view.navigation.openClassRegistration
import org.gestao.view.navigation.openEditClass
import org.gestao.view.navigation.classRegistrationNavigation
import org.gestao.view.navigation.editClassNavigation
import org.gestao.view.navigation.editClassItemNavigation
import org.gestao.view.newRegistrationIcon

@Composable
fun classScreen() {
    Card(
        modifier = Modifier
            .width(1200.dp)
            .height(800.dp)
            .padding(start = 200.dp,top = 30.dp,end = 40.dp, bottom = 40.dp)
    ) {
        Column(
            modifier = Modifier.background(backgroundCard)
        ) {
            Row (
                modifier = Modifier
                    .width(400.dp)
                    .background(backgroundCard, RoundedCornerShape(topEnd = 8.dp, bottomEnd = 50.dp))
            ){
                newRegistrationIcon(onClick = { openClassRegistration.value = !openClassRegistration.value })
                editRegistrationIcon(onClick = { openEditClass.value = !openEditClass.value })
            }

            classRegistrationNavigation()
            editClassNavigation()
            editClassItemNavigation()
        }
    }
}

