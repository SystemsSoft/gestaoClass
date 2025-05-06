package org.gestao.networking

import kotlinx.browser.window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import model.Acessos
import model.AcessosDto
import model.AcessosListDto
import org.bff.erp.util.BaseApi.BASE_SERVIDOR
import org.w3c.xhr.XMLHttpRequest

fun setCadastroAcessos(acessos: Acessos) {
    try {
        CoroutineScope(Dispatchers.Main).launch {

            XMLHttpRequest().apply {
                open("POST", "$BASE_SERVIDOR/acessos")
                setRequestHeader("Content-Type", "application/json")
                onload = {
                    if (status.toInt() == 201) {
                        println("Success: $responseText")
                        // handle success...
                    } else {
                        println("Error: $status $statusText")
                    }
                }
                onerror = {
                    println("Error in request: $status $statusText")
                }


                send(Json.encodeToString(acessos))
            }
        }
    } catch (e: Exception) {
        println("Error in setCadastroAcessos: ${e.message}")
    }
}

fun setAtualizarAcessos(acessos: AcessosDto) {
    try {
        CoroutineScope(Dispatchers.Main).launch {

            XMLHttpRequest().apply {
                open("PUT", "$BASE_SERVIDOR/acessos")
                setRequestHeader("Content-Type", "application/json")
                onload = {
                    if (status.toInt() == 201) {
                        println("Success: $responseText")
                        // handle success...
                    } else {
                        println("Error: $status $statusText")
                    }
                }
                onerror = {
                    println("Error in request: $status $statusText")
                }


                send(Json.encodeToString(acessos))
            }
        }
    } catch (e: Exception) {
        println("Error in setCadastroAcessos: ${e.message}")
    }
}

suspend fun fetchAllAcessos(): MutableList<AcessosDto> {
    val allAcessosList = mutableListOf<AcessosDto>()

    try {
        val response = window.fetch("$BASE_SERVIDOR/acessos").then {
                res -> res.text()
        }

        response.await<JsString>().toString().let { retorno ->
            val acessos: List<AcessosDto> = Json.decodeFromString(retorno)
            allAcessosList.addAll(acessos)
        }

    } catch (error: Throwable) {
        println("Fetch error: $error")
    }

    return allAcessosList
}