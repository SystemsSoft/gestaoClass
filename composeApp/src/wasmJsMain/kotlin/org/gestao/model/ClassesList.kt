package org.gestao.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.serialization.Serializable

@Serializable
class ClassesList() {
    var className = ""
    var codClass = ""
}

@Serializable
data class ClassesDto(
    val id: Int?,
    val className: String,
    val codClass: String,
)

class ClassesListDto {
    var id by mutableStateOf("")
    var className by mutableStateOf("")
    var codClass by mutableStateOf("")
}