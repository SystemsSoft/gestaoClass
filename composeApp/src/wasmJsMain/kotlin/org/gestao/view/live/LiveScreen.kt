package org.gestao.view.live

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import gestaoweb.bbf.com.util.Theme.backgroundCard
import kotlinx.browser.window
import kotlinx.coroutines.flow.MutableStateFlow
import org.gestao.view.classSelector
import org.gestao.viewmodel.bindAccessRegistration

val isMeeting = MutableStateFlow(false)
@Composable
fun liveClassScreen() {
    startMeet()
    Card(
        modifier = Modifier
            .width(1200.dp)
            .padding(start = 150.dp,top = 100.dp,end = 100.dp, bottom = 50.dp)
    ) {
        Column(
            modifier = Modifier.background(backgroundCard)
        ) {
            classSelector()

            Button(
                onClick = { isMeeting.value = true },
                modifier = Modifier
                    .padding(40.dp)
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Começar Aula", color = Color.Black)
            }
        }
    }
}

@Composable
fun startMeet() {
    if (isMeeting.collectAsState().value) {
        window.open("", "_blank")
    }
}

