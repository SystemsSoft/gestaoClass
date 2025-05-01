package model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.serialization.Serializable

@Serializable
class Acessos {
    var className = ""
    var codClass = ""
    var nome = ""
    var senha = ""
    var email = ""
}

class AcessosListDto {
    var className by mutableStateOf("")
    var codClass by mutableStateOf("")
    var nome by mutableStateOf("")
    var senha by mutableStateOf("")

    var email by mutableStateOf("")
}