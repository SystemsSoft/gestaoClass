package org.gestao.networking

import kotlinx.browser.window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.gestao.util.BaseApi.BASE_SERVER
import org.gestao.model.ClassDto
import org.gestao.model.ClassList
import org.gestao.viewmodel.requestStatus
import org.w3c.xhr.XMLHttpRequest

fun registerClass(classes: ClassList) {
    try {
        CoroutineScope(Dispatchers.Main).launch {

            XMLHttpRequest().apply {
                open("POST", "$BASE_SERVER/classes")
                setRequestHeader("Content-Type", "application/json")
                onload = { requestStatus.value = status.toInt() }

                onerror = { requestStatus.value = status.toInt() }


                send(Json.encodeToString(classes))
            }
        }
    } catch (e: Exception) {
        println("Error in registerClass: ${e.message}")
    }
}

suspend fun fetchAllClasses(): MutableList<ClassDto> {
    val allClassesList = mutableListOf<ClassDto>()

    try {
        val response = window.fetch("$BASE_SERVER/classes").then {
                res -> res.text()
        }

        response.await<JsString>().toString().let { retorno ->
            val classes: List<ClassDto> = Json.decodeFromString(retorno)
            allClassesList.addAll(classes)
        }

    } catch (error: Throwable) {
        println("Fetch error: $error")
    }

    return allClassesList
}

fun updateClasses(classDto: ClassDto) {
    try {
        CoroutineScope(Dispatchers.Main).launch {

            XMLHttpRequest().apply {
                open("PUT", "$BASE_SERVER/classes")
                setRequestHeader("Content-Type", "application/json")
                onload = { requestStatus.value = status.toInt() }

                onerror = { requestStatus.value = status.toInt() }

                send(Json.encodeToString(classDto))
            }
        }
    } catch (e: Exception) {
        println("Error in updateClasses: ${e.message}")
    }
}

fun deleteClasses(classDto: ClassDto) {
    try {
        CoroutineScope(Dispatchers.Main).launch {

            XMLHttpRequest().apply {
                open("DELETE", "$BASE_SERVER/classes")
                setRequestHeader("Content-Type", "application/json")
                onload = { requestStatus.value = status.toInt() }

                onerror = { requestStatus.value = status.toInt() }

                send(Json.encodeToString(classDto))
            }
        }
    } catch (e: Exception) {
        println("Error in setCadastroAcessos: ${e.message}")
    }
}