package org.gestao.networking

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.bff.erp.util.BaseApi.BASE_SERVIDOR
import org.gestao.model.ClassesList
import org.w3c.xhr.XMLHttpRequest

fun setCadastroClasse(classes: ClassesList) {
    try {
        CoroutineScope(Dispatchers.Main).launch {

            XMLHttpRequest().apply {
                open("POST", "$BASE_SERVIDOR/classes")
                setRequestHeader("Content-Type", "application/json")
                onload = {
                    if (status.toInt() == 201) {
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