package org.gestao.networking

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.gestao.util.BaseApi.BASE_S3
import org.gestao.util.BaseApi.BASE_SERVER
import org.gestao.model.UploadList
import org.gestao.util.fileSelected
import org.gestao.viewmodel.requestStatus
import org.w3c.xhr.XMLHttpRequest

fun registerUpload(uploadItem: UploadList) {
    try {
        CoroutineScope(Dispatchers.Main).launch {

            XMLHttpRequest().apply {
                open("POST", "$BASE_SERVER/upload")
                setRequestHeader("Content-Type", "application/json")
                onload = {
                    requestStatus.value = status.toInt()
                    uploadFile(status.toInt(),uploadItem)
                }

                onerror = { requestStatus.value = status.toInt() }

                send(Json.encodeToString(uploadItem))
            }
        }
    } catch (e: Exception) {
        println("Error in registerUpload: ${e.message}")
    }
}

private fun uploadFile(returnStatus: Int, item: UploadList) {
    if (returnStatus == 200) {
        try {
            XMLHttpRequest().apply {
                open(
                    "PUT",
                    "https://uploads-q1w2e3r4-s3.$BASE_S3/uploads/${item.classCode}/${item.fileCode}"
                )

                onload = { requestStatus.value = status.toInt() }

                onerror = { requestStatus.value = status.toInt() }

                send(fileSelected.value!!)
                fileSelected.value = null
            }
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }
}