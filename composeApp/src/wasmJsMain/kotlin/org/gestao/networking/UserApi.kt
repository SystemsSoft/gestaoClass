package org.gestao.networking

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.gestao.model.User
import org.gestao.util.BaseApi.BASE_SERVER
import org.gestao.viewmodel.authenticationFailed
import org.gestao.viewmodel.isUserValidated
import org.gestao.viewmodel.requestStatus
import org.w3c.xhr.XMLHttpRequest

 fun setUserValidate(user: User) {
     try {
         XMLHttpRequest().apply {
             open("POST", "$BASE_SERVER/auth")
             setRequestHeader("Content-Type", "application/json")
             onload = {
                 requestStatus.value = status.toInt()
                 if(this.responseText.toBooleanStrictOrNull() == true) {
                     isUserValidated.value = true
                 } else {
                     authenticationFailed.value = true
                 }
             }
             onerror = { requestStatus.value = status.toInt() }

             send(Json.encodeToString(user))
         }

     } catch (e: Exception) {
         println("Error in validateUser: ${e.message}")

     }
 }

