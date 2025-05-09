package org.gestao.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import model.Acessos
import model.AcessosDto
import model.AcessosListDto
import org.bff.erp.model.Usuario
import org.gestao.model.ClassesDto
import org.gestao.model.ClassesList
import org.gestao.model.ClassesListDto
import org.gestao.networking.fetchAllAcessos
import org.gestao.networking.fetchAllClasses
import org.gestao.networking.setAtualizarAcessos
import org.gestao.networking.setAtualizarClasses
import org.gestao.networking.setCadastroAcessos
import org.gestao.networking.setCadastroClasse
import org.gestao.networking.setExcluirAcessos
import org.gestao.networking.setExcluirClasses

var usuarioValidado = MutableStateFlow(false)
var falhaAutenticacao = MutableStateFlow(false)
var usuarioLogado  = MutableStateFlow(Usuario())

var requestStatus = MutableStateFlow(0)

var showDialogRetornoCadastro = MutableStateFlow(false)

var acessosDto = MutableStateFlow(AcessosListDto())
var classDto = MutableStateFlow(ClassesListDto())

val codSelected = MutableStateFlow("")

val className = MutableStateFlow("")

var allAcessos = MutableStateFlow<MutableList<AcessosDto>>(mutableListOf())

var allClasses = MutableStateFlow<MutableList<ClassesDto>>(mutableListOf())

fun getAllAcessos() {
    CoroutineScope(Dispatchers.Default).launch {
        allAcessos.value.addAll(fetchAllAcessos())
    }
}

fun getAllClasses() {
    CoroutineScope(Dispatchers.Default).launch {
        allClasses.value.addAll(fetchAllClasses())
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
fun bindAtualizarAcesso() {
    CoroutineScope(Dispatchers.Main).launch {
        setAtualizarAcessos(convertDtoToAcessosDto())
    }
}

fun bindAtualizarClasse() {
    CoroutineScope(Dispatchers.Main).launch {
        setAtualizarClasses(convertDtoToClasseDto())
    }
}

fun bindExcluirAcesso() {
    CoroutineScope(Dispatchers.Main).launch {
        setExcluirAcessos(convertDtoToAcessosDto())
    }
}

fun bindExcluirClasse() {
    CoroutineScope(Dispatchers.Main).launch {
        setExcluirClasses(convertDtoToClasseDto())
    }
}

fun bindCadastroClasse() {
    CoroutineScope(Dispatchers.Main).launch {
        setCadastroClasse(convertDtoToClassesList())
    }
}

fun convertDtoToAcessosList(): Acessos {
    return Acessos(
        codClass = codSelected.value,
        className = className.value,
        senha = acessosDto.value.senha,
        nome = acessosDto.value.nome,
        email = acessosDto.value.email,
    )
}
fun convertDtoToAcessosDto(): AcessosDto {
    return AcessosDto(
        id = acessosDto.value.id.toInt(),
        codClass = acessosDto.value.codClass,
        className = acessosDto.value.className,
        senha = acessosDto.value.senha,
        nome = acessosDto.value.nome,
        email = acessosDto.value.email,
    )
}
fun convertDtoToClasseDto(): ClassesDto {
    return ClassesDto(
        id = classDto.value.id.toInt(),
        codClass = classDto.value.codClass,
        className = classDto.value.className,
    )
}

fun convertDtoToClassesList(): ClassesList {
    return ClassesList().apply {
        codClass = classDto.value.codClass
        className = classDto.value.className
    }
}

