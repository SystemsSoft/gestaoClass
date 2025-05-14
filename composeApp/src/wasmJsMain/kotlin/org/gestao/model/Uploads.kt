package org.gestao.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.serialization.Serializable

@Serializable
data class UploadList(
    val fileName: String,
    val fileCode: String,
    val classCode: String,
    val fileType: String,
)

@Serializable
data class UploadDto(
    val id: Int?,
    val fileName: String,
    val fileCode: String,
    val classCode: String,
    val fileType: String,
)

class UploadsListDto {
    var id by mutableStateOf(0)
    var fileName by mutableStateOf("")
    var fileCode by mutableStateOf("")
    var classCode by mutableStateOf("")
    var fileType by mutableStateOf("")
}