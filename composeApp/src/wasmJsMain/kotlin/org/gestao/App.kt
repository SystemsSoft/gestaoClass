package org.gestao

import androidx.compose.runtime.*
import gestaoweb.bbf.com.util.Theme
import org.gestao.view.fieldLogOut
import org.gestao.view.loginScreen
import org.gestao.view.menu.navigationRail
import org.gestao.view.menu.setupNavigationMenu
import org.gestao.view.navigation.accessNavigation
import org.gestao.view.navigation.classNavigation
import org.gestao.view.navigation.uploadNavigation
import org.gestao.view.observeRequestStatus
import org.gestao.viewmodel.allAccesses
import org.gestao.viewmodel.allClasses
import org.gestao.viewmodel.getAllAccesses
import org.gestao.viewmodel.getAllClasses
import org.gestao.viewmodel.isUserValidated

@Composable
fun App() {
    Theme.MyAppTheme {
        if (!isUserValidated.collectAsState().value) {
            loadData()
            observeRequestStatus()
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
    accessNavigation()
    classNavigation()
    uploadNavigation()
}

fun loadData() {
    allAccesses.value.clear()
    allClasses.value.clear()
    getAllAccesses()
    getAllClasses()
}