package org.gestao.view.live

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.compose.ui.Alignment

@Composable
fun liveClassScreen() {
    val fakeParticipants = remember { List(6) { "Usuário ${it + 1}" } }

    Surface(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp),
        color = Color(0xFF202124)
    ) {
        Box {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                VideoGrid(participants = fakeParticipants)
                ControlButtons()
            }
        }
    }
}

@Composable
fun VideoGrid(participants: List<String>) {
    val rows = participants.chunked(3)
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(bottom = 80.dp) // Espaço pros botões
    ) {
        rows.forEach { row ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                row.forEach { participant ->
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 5.dp, vertical = 10.dp)
                            .weight(1f)
                            .aspectRatio(16 / 9f)
                            .background(Color.DarkGray, RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            participant,
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                repeat(3 - row.size) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun ControlButtons() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        MeetButton("Iniciar Aula", Color(0xFF34A853)) { /* ação */ }
        Spacer(Modifier.width(20.dp))
        MeetButton("Selecionar Classe", Color(0xFF4285F4)) { /* ação */ }
        Spacer(Modifier.width(20.dp))
        MeetButton("Compartilhar", Color(0xFFF9AB00)) { /* ação */ }
        Spacer(Modifier.width(20.dp))
        MeetButton("Encerrar", Color(0xFFEA4335)) { /* ação */ }
    }
}

@Composable
fun MeetButton(text: String, color: Color, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .height(50.dp)
            .clickable { onClick() },
        color = color,
        shape = RoundedCornerShape(25.dp),
        elevation = 6.dp
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text,
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
