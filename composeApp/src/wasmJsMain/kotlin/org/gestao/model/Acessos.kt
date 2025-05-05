package model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.serialization.Serializable

@Serializable
data class Acessos(
    val className: String,
    val codClass: String,
    val nome: String,
    val senha: String,
    val email: String,
)

@Serializable
data class AcessosDto(
    val id: Int?,
    val className: String,
    val codClass: String,
    val nome: String,
    val senha: String,
    val email: String,
)


class AcessosListDto {
    var className by mutableStateOf("")

    var codClass by mutableStateOf("")
    var nome by mutableStateOf("")
    var senha by mutableStateOf("")
    var email by mutableStateOf("")
}