package org.gestao

import androidx.compose.runtime.*
import gestaoweb.bbf.com.util.Theme
import org.gestao.view.Acessos.openAcessoScreen
import org.gestao.view.fieldLogOut
import org.gestao.view.loginScreen
import org.gestao.view.menu.navigationRail
import org.gestao.view.menu.setupNavigation
import org.gestao.viewmodel.usuarioValidado

@Composable
fun App() {
    Theme.MyAppTheme {
        if (!usuarioValidado.collectAsState().value) {
            openAcessoScreen()
            setupNavigation()
            navigationRail()
            fieldLogOut()
        } else {
            loginScreen()
        }
    }
}