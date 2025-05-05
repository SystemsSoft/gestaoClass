package org.gestao.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import model.Acessos
import model.AcessosListDto
import org.bff.erp.model.Usuario
import org.gestao.model.ClassesList
import org.gestao.model.ClassesListDto
import org.gestao.networking.fetchAllAcessos
import org.gestao.networking.setCadastroAcessos
import org.gestao.networking.setCadastroClasse

var usuarioValidado = MutableStateFlow(false)
var falhaAutenticacao = MutableStateFlow(false)
var usuarioLogado  = MutableStateFlow(Usuario())

var retornoStatusCadastroAcesso = MutableStateFlow(0)

var showDialogRetornoCadastro = MutableStateFlow(false)

var acessosDto = MutableStateFlow(AcessosListDto())
var classDto = MutableStateFlow(ClassesListDto())
var classSelected = MutableStateFlow(ClassesList())

var allAcessos = MutableStateFlow<MutableList<Acessos>>(mutableListOf())

fun getAllAcessos() {
    CoroutineScope(Dispatchers.Default).launch {
        allAcessos.value.addAll(fetchAllAcessos())
    }
}

fun validarUsuario(nomeUsuario: String, senhaUsuario: String) {
    if(nomeUsuario == "hml" && senhaUsuario == "01") {
        usuarioValidado.value = true
    } else {
        falhaAutenticacao.value = true
    }
}

fun bindCadastroAcesso() {
    CoroutineScope(Dispatchers.Main).launch {
        setCadastroAcessos(convertDtoToAcessosList())
    }
}

fun bindCadastroClasse() {
    CoroutineScope(Dispatchers.Main).launch {
        setCadastroClasse(convertDtoToClassesList())
    }
}

fun convertDtoToAcessosList(): Acessos {
    return Acessos().apply {
        codClass = classSelected.value.codClass
        className = classSelected.value.className
        senha = acessosDto.value.senha
        nome = acessosDto.value.nome
        email = acessosDto.value.email
    }
}
fun convertDtoToClassesList(): ClassesList {
    return ClassesList().apply {
        codClass = classDto.value.codClass
        className = classDto.value.className
    }
}

