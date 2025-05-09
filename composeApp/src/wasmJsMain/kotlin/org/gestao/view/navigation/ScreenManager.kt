package org.gestao.view.navigation

import org.gestao.view.menu.acessosScreen
import org.gestao.view.menu.classesScreen

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