package org.gestao

import androidx.compose.runtime.*
import gestaoweb.bbf.com.util.Theme
import org.gestao.view.fieldLogOut
import org.gestao.view.loginScreen
import org.gestao.view.menu.navigationRail
import org.gestao.view.menu.setupNavigationMenu
import org.gestao.view.navigation.accessNavigation
import org.gestao.view.navigation.classNavigation
import org.gestao.view.navigation.dashNavigation
import org.gestao.view.navigation.uploadNavigation
import org.gestao.view.observeRequestStatus
import org.gestao.viewmodel.allAccesses
import org.gestao.viewmodel.allClasses
import org.gestao.viewmodel.allUploads
import org.gestao.viewmodel.getAllAccesses
import org.gestao.viewmodel.getAllClasses
import org.gestao.viewmodel.getAllUploads
import org.gestao.viewmodel.isUserValidated

@Composable
fun App() {
    loadData()
    setupAppUI()
}

@Composable
fun setupAppUI() {
    Theme.MyAppTheme {
        if (isUserValidated.collectAsState().value) {
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
    dashNavigation()
    accessNavigation()
    classNavigation()
    uploadNavigation()
}

fun loadData() {
    allAccesses.value.clear()
    allClasses.value.clear()
    allUploads.value.clear()
    getAllAccesses()
    getAllClasses()
    getAllUploads()
}