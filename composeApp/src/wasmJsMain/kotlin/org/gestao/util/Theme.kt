package gestaoweb.bbf.com.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


object Theme {

    val transparentColor = Color(0x00000000)


    val  darkBlueColorTransparent = Color(0x340a1f44)
    val  colorIconClient = Color(0xfffc7900)
    val  darkBlueColor = Color(0xbb0a1f44)
    val  btAuthColor = Color(0xff223b65)

    val  borderColor = Color(0x995d6987)

    val backgroundCard = Color(0xFFF0F2F5) // Cor de fundo para cards gerais
    val backgroundCardDash = Color(0xFFFFFFFF) // Cor de fundo para os cards do dashboard
    val PrimaryBlue = Color(0xFF3F51B5) // Um azul primário para destacar
    val TextDark = Color(0xFF333333) // Cor de texto padrão
    val TextLight = Color(0xFF666666) // Co
    val heightField = 55.dp
    val fontDefault = 12.sp
    val gradientBackground = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF597395),
            Color(0xFF005F73),
            Color(0xFF0A1F44)
        )
    )

    val loginBackgroundBrush = Brush.linearGradient(
        colors = listOf(
            Color(0xffb8bcc3),
            Color(0xFFF7F8FA),
            Color(0xffb8bcc3),
            Color(0xFFEDF0F5)
        ),
        start = Offset.Zero,
        end = Offset.Infinite
    )


    private val LightColorPalette = lightColors(
        primary = Color.LightGray,

    )

    @Composable
    fun MyAppTheme(content: @Composable () -> Unit) {
        MaterialTheme(
            colors = LightColorPalette,
            content = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(gradientBackground)
                ) {
                    content()
                }
            }
        )
    }
}
