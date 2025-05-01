package org.gestao.networking

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import model.Acessos
import org.bff.erp.util.BaseApi.BASE_SERVIDOR
import org.gestao.viewmodel.retornoStatusCadastroAcesso
import org.w3c.xhr.XMLHttpRequest

fun setCadastroAcessos(acessos: Acessos) {
    try {
        CoroutineScope(Dispatchers.Main).launch {
            println("Sending Acessos: $acessos")

            XMLHttpRequest().apply {
                open("POST", "$BASE_SERVIDOR/acessos/add")
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
