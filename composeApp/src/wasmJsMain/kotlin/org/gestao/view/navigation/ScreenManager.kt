package org.gestao.view.navigation

import org.gestao.view.menu.accessScreen
import org.gestao.view.menu.classesScreen
import org.gestao.view.menu.uploadScreen

fun closeAccessScreen() {
    accessScreen.value = false
    openEditAccess.value = false
    openEditItemAccess.value = false
}

fun closeClassScreen() {
    classesScreen.value = false
    openEditClass.value = false
    openEditClassItem.value = false
}

fun closeUploadScreen() {
    uploadScreen.value = false
    openEditUpload.value = false
    abrirEditarItemUpload.value = false
}