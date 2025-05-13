package org.gestao.view.navigation

import org.gestao.view.menu.acessosScreen
import org.gestao.view.menu.classesScreen
import org.gestao.view.menu.uploadScreen

fun closeAcessoScreen() {
    acessosScreen.value = false
    abrirEditarAcesso.value = false
    abrirEditarItemAcesso.value = false
}

fun closeClasseScreen() {
    classesScreen.value = false
    abrirEditarClasse.value = false
    abrirEditarItemClasse.value = false
}

fun closeUploadScreen() {
    uploadScreen.value = false
    abrirEditarUpload.value = false
    abrirEditarItemUpload.value = false
}