package org.gestao.view

import org.gestao.view.menu.accessScreen
import org.gestao.view.menu.classesScreen
import org.gestao.view.menu.dashScreen
import org.gestao.view.menu.uploadScreen
import org.gestao.view.navigation.abrirEditarItemUpload
import org.gestao.view.navigation.openEditAccess
import org.gestao.view.navigation.openEditClass
import org.gestao.view.navigation.openEditClassItem
import org.gestao.view.navigation.openEditItemAccess
import org.gestao.view.navigation.openEditUpload

fun closeAccessScreen() {
    accessScreen.value = false
    openEditAccess.value = false
    openEditItemAccess.value = false
    classFilterIcon.value = false
}

fun closeClassScreen() {
    classesScreen.value = false
    openEditClass.value = false
    openEditClassItem.value = false
    classFilterIcon.value = false
}

fun closeUploadScreen() {
    uploadScreen.value = false
    openEditUpload.value = false
    abrirEditarItemUpload.value = false
    classFilterIcon.value = false
}

fun closeDashScreen() {
    dashScreen.value = false
}