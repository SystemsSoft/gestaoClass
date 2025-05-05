package org.gestao

import androidx.compose.runtime.*
import gestaoweb.bbf.com.util.Theme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.gestao.view.classes.openClasseScreen
import org.gestao.view.fieldLogOut
import org.gestao.view.loginScreen
import org.gestao.view.menu.navigationRail
import org.gestao.view.menu.setupNavigationMenu
import org.gestao.view.navigation.acessoNavigation
import org.gestao.view.navigation.cadastroAcessoNavigation
import org.gestao.view.navigation.editarAcessoNavigation
import org.gestao.viewmodel.getAllAcessos
import org.gestao.viewmodel.usuarioValidado

@Composable
fun App() {
    Theme.MyAppTheme {
        if (!usuarioValidado.collectAsState().value) {
            loadData()
            setupNavigation()
            openClasseScreen()
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
}

fun loadData() {
    getAllAcessos()
}