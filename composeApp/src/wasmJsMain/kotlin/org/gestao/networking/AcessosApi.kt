package org.gestao.networking

import kotlinx.browser.window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import model.Access
import model.AccessDto
import org.gestao.util.BaseApi.BASE_SERVER
import org.gestao.viewmodel.requestStatus
import org.w3c.xhr.XMLHttpRequest

fun registerAccess(access: Access) {
    try {
        CoroutineScope(Dispatchers.Main).launch {

            XMLHttpRequest().apply {
                open("POST", "$BASE_SERVER/accesses")
                setRequestHeader("Content-Type", "application/json")
                onload = { requestStatus.value = status.toInt() }

                onerror = { requestStatus.value = status.toInt() }

                send(Json.encodeToString(access))
            }
        }
    } catch (e: Exception) {
        println("Error in registerAccess: ${e.message}")
    }
}

fun updateAccess(access: AccessDto) {
    try {
        CoroutineScope(Dispatchers.Main).launch {

            XMLHttpRequest().apply {
                open("PUT", "$BASE_SERVER/accesses")
                setRequestHeader("Content-Type", "application/json")
                onload = { requestStatus.value = status.toInt() }

                onerror = { requestStatus.value = status.toInt() }


                send(Json.encodeToString(access))
            }
        }
    } catch (e: Exception) {
        println("Error in updateAccess: ${e.message}")
    }
}

fun deleteAccess(access: AccessDto) {
    try {
        CoroutineScope(Dispatchers.Main).launch {

            XMLHttpRequest().apply {
                open("DELETE", "$BASE_SERVER/accesses")
                setRequestHeader("Content-Type", "application/json")
                onload = { requestStatus.value = status.toInt() }

                onerror = { requestStatus.value = status.toInt() }

                send(Json.encodeToString(access))
            }
        }
    } catch (e: Exception) {
        println("Error in deleteAccess: ${e.message}")
    }
}

suspend fun fetchAllAccesses(): MutableList<AccessDto> {
    val allAccessList = mutableListOf<AccessDto>()

    try {
        val response = window.fetch("$BASE_SERVER/accesses").then {
                res -> res.text()
        }

        response.await<JsString>().toString().let { retorno ->
            val accesses: List<AccessDto> = Json.decodeFromString(retorno)
            allAccessList.addAll(accesses)
        }

    } catch (error: Throwable) {
        println("Fetch error: $error")
    }

    return allAccessList
}