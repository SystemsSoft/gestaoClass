package model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.serialization.Serializable

@Serializable
data class Access(
    val className: String,
    val classCode: String,
    val name: String,
    val password: String,
    val email: String,
)

@Serializable
data class AccessDto(
    val id: Int?,
    val className: String,
    val classCode: String,
    val name: String,
    val password: String,
    val email: String,
)


class AccessListDto {
    var id by mutableStateOf("")

    var className by mutableStateOf("")

    var classCode by mutableStateOf("")
    var name by mutableStateOf("")
    var password by mutableStateOf("")
    var email by mutableStateOf("")
}