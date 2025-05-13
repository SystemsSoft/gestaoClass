package org.gestao.view

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import gestaoclass.composeapp.generated.resources.Res
import gestaoclass.composeapp.generated.resources.ic_editar
import gestaoclass.composeapp.generated.resources.ic_novo
import gestaoweb.bbf.com.util.Theme.colorIconClient
import gestaoweb.bbf.com.util.Theme.fontDefault
import org.jetbrains.compose.resources.painterResource

@Composable
fun newRegistrationIcon(onClick: () -> Unit) {
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
fun editRegistrationIcon(onClick: () -> Unit) {
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
