package org.gestao.view.live


import androidx.compose.runtime.*
import kotlinx.browser.document
import org.w3c.dom.HTMLIFrameElement


@Composable
fun liveClassScreen() {
    val iframeId = "videoCallFrame"
    val containerId = "iframe-container"
    var iframeAppended by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (!iframeAppended) {
            val container = document.getElementById(containerId)
            if (container != null && container.childElementCount == 0) {
                val iframe = document.createElement("iframe") as HTMLIFrameElement
                iframe.id = iframeId
                iframe.src = "meet.html"
                iframe.width = "800"
                iframe.height = "600"
                iframe.style.border = "none"
                iframe.style.display = "block"
                iframe.style.margin = "0 auto"
                container.appendChild(iframe)
                iframeAppended = true
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            document.getElementById(iframeId)?.remove()
            iframeAppended = false
        }
    }
}
