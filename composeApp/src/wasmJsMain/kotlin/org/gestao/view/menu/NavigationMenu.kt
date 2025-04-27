package org.gestao.view.menu

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.NavigationRail
import androidx.compose.material.NavigationRailItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import gestaoclass.composeapp.generated.resources.Res
import gestaoclass.composeapp.generated.resources.logo
import gestaoweb.bbf.com.util.Menu.iconsMenu
import gestaoweb.bbf.com.util.Menu.menuListNames
import gestaoweb.bbf.com.util.Theme
import gestaoweb.bbf.com.util.Theme.colorIconClient
import kotlinx.coroutines.flow.MutableStateFlow
import org.gestao.view.dashBoardScreen
import org.jetbrains.compose.resources.painterResource

val itemMenuSelected = MutableStateFlow(0)
var acessosScreen  = MutableStateFlow (false)


@Composable
fun setupNavigation() {
    itemMenuSelected.collectAsState().value.let {
        when (it) {
            0 -> dashBoardScreen()
            1 -> "Uploads"
            2 -> acessosScreen.value = !acessosScreen.value
            3 -> "Classes"

            else -> {}
        }
    }
}

@Composable
fun navigationRail() {
    val itemSelected by itemMenuSelected.collectAsState()

    NavigationRail(
        modifier = Modifier
            .padding(top = 140.dp)
            .width(180.dp),
        backgroundColor = Theme.transparentColor,
        elevation = 0.dp
    ) {
        menuListNames.forEachIndexed { index, item ->
            val isSelected = itemSelected == index

            val isHovered by remember { mutableStateOf(false) }
            val size by animateDpAsState(targetValue = when {
                isHovered -> 65.dp
                isSelected -> 28.dp
                else -> 24.dp
            })

            NavigationRailItem(
                icon = {
                    Icon(
                        iconsMenu()[index],
                        contentDescription = null,
                        modifier = Modifier.size(size),
                        tint = if (isSelected) Color(0xFFFC7900) else Color.White
                    )
                },
                label = {
                    Text(
                        item,
                        modifier = Modifier.padding(vertical = 12.dp),
                        style = TextStyle(
                            color = if (isSelected) Color(0xFFFC7900) else Color.White,
                            fontSize = 10.sp
                        )
                    )
                },
                selected = isSelected,
                onClick = {
                    if (index == itemMenuSelected.value ){
                        acessosScreen.value = false
                        itemMenuSelected.value = 0
                    } else {
                       itemMenuSelected.value = index
                    }
                },
            )
        }
    }
}