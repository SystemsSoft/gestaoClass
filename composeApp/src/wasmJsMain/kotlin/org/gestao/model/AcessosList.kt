package model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class AcessosList {
    var className = ""
    var codClass = ""
    var nome = ""
    var senha = ""
}

class AcessosListDto {
    var className by mutableStateOf("")
    var codClass by mutableStateOf("")
    var nome by mutableStateOf("")
    var senha by mutableStateOf("")
}