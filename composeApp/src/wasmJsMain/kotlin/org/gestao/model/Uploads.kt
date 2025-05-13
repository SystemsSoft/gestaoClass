package org.gestao.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.serialization.Serializable

@Serializable
data class UploadList(
    val fileName: String,
    val codFile: String,
    val codClass: String,
    val tipoFile: String,
)

@Serializable
data class UploadDto(
    val id: Int?,
    val fileName: String,
    val codFile: String,
    val codClass: String,
    val tipoFile: String,
)

class UploadsListDto {
    var id by mutableStateOf("")
    var fileName by mutableStateOf("")
    var codFile by mutableStateOf("")
    var codClass by mutableStateOf("")
    var tipoFile by mutableStateOf("")
}