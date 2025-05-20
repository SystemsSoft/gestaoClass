package org.gestao.view.live

import androidx.compose.runtime.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp

@Composable
fun liveClassScreen() {
    val fakeParticipants = remember { List(12) { "Usuário ${it + 1}" } }

    Surface(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 15.dp, top = 15.dp, end = 15.dp, bottom = 15.dp),
        color = Color(0xFF202124)
    ) {
        VideoGrid(participants = fakeParticipants)
    }
}

@Composable
fun VideoGrid(participants: List<String>) {
    val rows = participants.chunked(3) // Máximo 3 por linha
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        rows.forEach { row ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth(),
            ) {
                row.forEach { participant ->
                    Box(
                        modifier = Modifier
                            .padding(start = 5.dp, end = 5.dp, top = 10.dp)
                            .weight(1f)
                            .aspectRatio(16 / 9f)
                            .background(Color.DarkGray, RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            participant,
                            color = Color.White,
                            fontSize = 18.sp
                        )
                    }
                }
                // Preencher espaço restante se linha incompleta
                repeat(6 - row.size) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}
