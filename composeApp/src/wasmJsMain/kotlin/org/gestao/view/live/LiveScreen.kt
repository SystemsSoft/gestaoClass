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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import gestaoclass.composeapp.generated.resources.Res
import gestaoclass.composeapp.generated.resources.ic_call_end
import gestaoclass.composeapp.generated.resources.ic_cam_on
import gestaoclass.composeapp.generated.resources.ic_groups
import gestaoclass.composeapp.generated.resources.ic_mic_on
import gestaoclass.composeapp.generated.resources.ic_play
import gestaoclass.composeapp.generated.resources.ic_share
import gestaoweb.bbf.com.util.Theme.transparentColor
import org.jetbrains.compose.resources.painterResource


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
        Box(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier.fillMaxSize().padding(start = 5.dp, top = 5.dp, bottom = 80.dp)) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(Color.DarkGray, RoundedCornerShape(12.dp))
                ) {
                    Text(
                        "Tela Compartilhada",
                        color = Color.White,
                        modifier = Modifier
                            .padding(start = 5.dp, top = 5.dp)
                            .align(Alignment.Center),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                ParticipantColumn(participants = fakeParticipants)
            }
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .background(Color(0xFF202124))
            ) {
                ControlButtons()
            }
        }
    }
}

@Composable
fun ParticipantColumn(participants: List<String>) {
    val rows = participants.chunked(2)

    Column(
        modifier = Modifier
            .width(300.dp)
            .fillMaxHeight()
            .padding(start = 5.dp, top = 5.dp, bottom = 5.dp, end = 5.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        rows.forEach { row ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16 / 9f),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                row.forEach { participant ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .background(Color.Gray, RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = participant,
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                // Preenche espaço se a linha tiver só 1 item
                if (row.size == 1) {
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
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        MeetButton(painterResource(Res.drawable.ic_mic_on), "Áudio", transparentColor) { /* ação */ }
        Spacer(Modifier.width(15.dp))
        MeetButton(painterResource(Res.drawable.ic_cam_on), "Video", transparentColor) { /* ação */ }
        Spacer(Modifier.width(15.dp))
        MeetButton(painterResource(Res.drawable.ic_play), "Iniciar Aula", transparentColor) { /* ação */ }
        Spacer(Modifier.width(15.dp))
        MeetButton(painterResource(Res.drawable.ic_groups), "Selecionar Classe", transparentColor) { /* ação */ }
        Spacer(Modifier.width(15.dp))
        MeetButton(painterResource(Res.drawable.ic_share), "Compartilhar", transparentColor) { /* ação */ }
        Spacer(Modifier.width(15.dp))
        MeetButton(painterResource(Res.drawable.ic_call_end), "Encerrar", transparentColor) { /* ação */ }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MeetButton(icon: Painter, tooltip: String, color: Color, onClick: () -> Unit) {
    var isHovered by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .offset(y = (-40).dp)
            .background(
                color = Color.Black,
                shape = RoundedCornerShape(18.dp)
            )
            .padding(4.dp)
            .onPointerEvent(PointerEventType.Enter) { isHovered = true }
            .onPointerEvent(PointerEventType.Exit) { isHovered = false },
        contentAlignment = Alignment.Center
    ) {
        if (isHovered) {
            Box(
                modifier = Modifier
                    .offset(y = (-60).dp)
                    .background(transparentColor)
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = tooltip,
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
        }

        Surface(
            modifier = Modifier
                .height(50.dp)
                .width(50.dp)
                .clickable { onClick() },
            color = color,
            shape = RoundedCornerShape(25.dp),
            elevation = 6.dp
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    icon,
                    contentDescription = tooltip,
                    tint = Color.White
                )
            }
        }
    }
}
