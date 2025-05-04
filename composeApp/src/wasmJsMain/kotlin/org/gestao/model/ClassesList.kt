package org.gestao.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.serialization.Serializable

class ClassesList(string: String, string1: String) {
    var className = ""
    var codClass = ""
}

class ClassesListDto {
    var className by mutableStateOf("")
    var codClass by mutableStateOf("")
}