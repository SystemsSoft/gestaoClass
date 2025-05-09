package org.gestao

import androidx.compose.runtime.*
import gestaoweb.bbf.com.util.Theme
import org.gestao.view.fieldLogOut
import org.gestao.view.loginScreen
import org.gestao.view.menu.navigationRail
import org.gestao.view.menu.setupNavigationMenu
import org.gestao.view.navigation.acessoNavigation
import org.gestao.view.navigation.classeNavigation
import org.gestao.viewmodel.getAllAcessos
import org.gestao.viewmodel.getAllClasses
import org.gestao.viewmodel.usuarioValidado

@Composable
fun App() {
    Theme.MyAppTheme {
        if (!usuarioValidado.collectAsState().value) {
            loadData()
            setupNavigation()
            setupNavigationMenu()
            navigationRail()
            fieldLogOut()
        } else {
            loginScreen()
        }
    }
}
@Composable
fun setupNavigation() {
    acessoNavigation()
    classeNavigation()
}

fun loadData() {
    getAllAcessos()
    getAllClasses()
}