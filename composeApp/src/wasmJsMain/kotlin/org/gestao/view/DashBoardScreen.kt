package org.gestao.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import gestaoweb.bbf.com.util.Theme.backgroundCard
import org.gestao.viewmodel.allAccesses
import org.gestao.viewmodel.allClasses


@Composable
fun dashBoardScreen() {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Card(
            modifier = Modifier
                .width(1200.dp)
                .height(500.dp)
                .padding(start = 150.dp, top = 100.dp, end = 100.dp, bottom = 50.dp),
            shape = RoundedCornerShape(16.dp)

        ) {


        }
        Row {
            accessCard()
            classCard()
        }
    }
}

@Composable
fun accessCard() {
    val getAllAccesses by allAccesses.collectAsState()

    Card(
        modifier = Modifier
            .width(200.dp)
            .height(100.dp)
            .offset(x = 180.dp, y = 50.dp)
            .zIndex(1f),
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(
            modifier = Modifier
                .background(backgroundCard)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column {
                Text(
                    text = "Acessos",
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = Color.Black,
                    )
                )
                Text(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .align(Alignment.CenterHorizontally),
                    text = getAllAccesses.size.toString(),
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = Color.Black,
                    )
                )
            }
        }
    }
}

@Composable
fun classCard() {
    val getAllClasses by allClasses.collectAsState()

    Card(
        modifier = Modifier
            .width(200.dp)
            .height(100.dp)
            .padding(start = 10.dp)
            .offset(x = 180.dp, y = 50.dp)
            .zIndex(1f),
        shape = RoundedCornerShape(8.dp)
    ) {
        Box(
            modifier = Modifier
                .background(backgroundCard)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column {
                Text(
                    text = "Classes",
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = Color.Black,
                    )
                )
                Text(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .align(Alignment.CenterHorizontally),
                    text = getAllClasses.size.toString(),
                    style = TextStyle(
                        fontSize = 12.sp,
                        color = Color.Black,
                    )
                )
            }
        }
    }
}


