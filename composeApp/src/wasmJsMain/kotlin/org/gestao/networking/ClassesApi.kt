package org.gestao.networking

import kotlinx.browser.window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.bff.erp.util.BaseApi.BASE_SERVIDOR
import org.gestao.model.ClassesDto
import org.gestao.model.ClassesList
import org.w3c.xhr.XMLHttpRequest

fun setCadastroClasse(classes: ClassesList) {
    try {
        CoroutineScope(Dispatchers.Main).launch {

            XMLHttpRequest().apply {
                open("POST", "$BASE_SERVIDOR/classes")
                setRequestHeader("Content-Type", "application/json")
                onload = {
                    if (status.toInt() == 200) {
                        println("Success: $responseText")

                    } else {
                        println("Error: $status $statusText")
                    }
                }
                onerror = {
                    println("Error in request: $status $statusText")
                }


                send(Json.encodeToString(classes))
            }
        }
    } catch (e: Exception) {
        println("Error in setCadastroClasse: ${e.message}")
    }
}

suspend fun fetchAllClasses(): MutableList<ClassesDto> {
    val allClassesList = mutableListOf<ClassesDto>()

    try {
        val response = window.fetch("$BASE_SERVIDOR/classes").then {
                res -> res.text()
        }

        response.await<JsString>().toString().let { retorno ->
            val classes: List<ClassesDto> = Json.decodeFromString(retorno)
            allClassesList.addAll(classes)
        }

    } catch (error: Throwable) {
        println("Fetch error: $error")
    }

    return allClassesList
}

fun setAtualizarClasses(convertDtoToClasseDto: ClassesDto) {
    try {
        CoroutineScope(Dispatchers.Main).launch {

            XMLHttpRequest().apply {
                open("PUT", "$BASE_SERVIDOR/classes")
                setRequestHeader("Content-Type", "application/json")
                onload = {
                    if (status.toInt() == 200) {
                        println("Success: $responseText")
                        // handle success...
                    } else {
                        println("Error: $status $statusText")
                    }
                }
                onerror = {
                    println("Error in request: $status $statusText")
                }

                send(Json.encodeToString(convertDtoToClasseDto))
            }
        }
    } catch (e: Exception) {
        println("Error in setCadastroAcessos: ${e.message}")
    }
}

fun setExcluirClasses(convertDtoToClasseDto: ClassesDto) {
    try {
        CoroutineScope(Dispatchers.Main).launch {

            XMLHttpRequest().apply {
                open("DELETE", "$BASE_SERVIDOR/classes")
                setRequestHeader("Content-Type", "application/json")
                onload = {
                    if (status.toInt() == 200) {
                        println("Success: $responseText")
                        // handle success...
                    } else {
                        println("Error: $status $statusText")
                    }
                }
                onerror = {
                    println("Error in request: $status $statusText")
                }

                send(Json.encodeToString(convertDtoToClasseDto))
            }
        }
    } catch (e: Exception) {
        println("Error in setCadastroAcessos: ${e.message}")
    }
}