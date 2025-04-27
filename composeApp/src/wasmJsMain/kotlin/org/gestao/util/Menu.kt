package gestaoweb.bbf.com.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import gestaoclass.composeapp.generated.resources.Res

import gestaoclass.composeapp.generated.resources.dashboard
import gestaoclass.composeapp.generated.resources.ic_add_acesso
import gestaoclass.composeapp.generated.resources.ic_aulas
import gestaoclass.composeapp.generated.resources.ic_upload
import org.jetbrains.compose.resources.painterResource

object Menu {

    val menuListNames = listOf(
        "DashBoard","Uploads","Acessos","Classes"
    )

    @Composable
    fun iconsMenu(): List<Painter> {
    return  listOf(
            painterResource(Res.drawable.dashboard),
            painterResource(Res.drawable.ic_upload),
            painterResource(Res.drawable.ic_add_acesso),
            painterResource(Res.drawable.ic_aulas),
        )
    }
}