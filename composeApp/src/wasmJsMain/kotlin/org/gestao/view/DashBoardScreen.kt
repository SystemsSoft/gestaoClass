package com.example.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gestaoweb.bbf.com.util.Theme
import org.gestao.viewmodel.allAccesses
import org.gestao.viewmodel.allClasses


@Composable
fun dashBoardScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Theme.transparentColor), // Adiciona um fundo para a tela toda
        contentAlignment = Alignment.Center // Centraliza o conteúdo do dashboard na tela
    ) {
        // O card principal de fundo para a área do dashboard
        Card(
            modifier = Modifier
                .width(600.dp)
                .height(400.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = 8.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Visão Geral do Dashboard",
                    style = TextStyle(
                        fontSize = 28.sp, // Aumentado para mais destaque
                        fontWeight = FontWeight.ExtraBold, // Mais peso
                        color = Theme.TextDark
                    ),
                    modifier = Modifier.padding(bottom = 32.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround, // Espaço em volta, mais distribuído
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val currentAccesses = allAccesses.collectAsState().value.size.toString()
                    QuantityCard(
                        title = "Total de Acessos",
                        value = currentAccesses,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(Modifier.width(32.dp))
                    val currentClasses = allClasses.collectAsState().value.size.toString()
                    QuantityCard(
                        title = "Total de Classes",
                        value = currentClasses,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
fun QuantityCard(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .height(160.dp)
            .background(Color.White, RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(16.dp),
        elevation = 6.dp // Mais elevação para destaque
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Theme.backgroundCardDash)
                .padding(20.dp), // Aumenta o padding interno
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = title,
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = Theme.TextLight, // Cor mais clara para o título
                        fontWeight = FontWeight.Normal
                    ),
                    modifier = Modifier.padding(bottom = 12.dp) // Mais espaço abaixo do título
                )
                Text(
                    text = value,
                    style = TextStyle(
                        fontSize = 48.sp, // Tamanho da fonte maior para o valor
                        color = Theme.PrimaryBlue, // Cor primária para o valor
                        fontWeight = FontWeight.ExtraBold // Negrito extra
                    )
                )
            }
        }
    }
}