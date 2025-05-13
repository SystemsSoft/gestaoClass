package org.gestao.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.serialization.Serializable

@Serializable
class ClassList() {
    var className = ""
    var classCode = ""
}

@Serializable
data class ClassDto(
    val id: Int?,
    val className: String,
    val classCode: String,
)

class ClassListDto {
    var id by mutableStateOf("")
    var className by mutableStateOf("")
    var classCode by mutableStateOf("")
}