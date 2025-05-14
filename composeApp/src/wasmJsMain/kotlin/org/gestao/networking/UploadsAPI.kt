package org.gestao.networking

import kotlinx.browser.window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.await
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.gestao.model.UploadDto
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

suspend fun fetchAllUploads(): MutableList<UploadDto> {
    val allUploadList = mutableListOf<UploadDto>()

    try {
        val response = window.fetch("$BASE_SERVER/upload").then {
                res -> res.text()
        }

        response.await<JsString>().toString().let { retorno ->
            val uploads: List<UploadDto> = Json.decodeFromString(retorno)
            allUploadList.addAll(uploads)
        }

    } catch (error: Throwable) {
        println("Fetch error: $error")
    }

    return allUploadList
}

fun updateFile(file: UploadDto) {
    try {
        CoroutineScope(Dispatchers.Main).launch {

            XMLHttpRequest().apply {
                open("PUT", "$BASE_SERVER/upload")
                setRequestHeader("Content-Type", "application/json")
                onload = { requestStatus.value = status.toInt() }

                onerror = { requestStatus.value = status.toInt() }


                send(Json.encodeToString(file))
            }
        }
    } catch (e: Exception) {
        println("Error in updateAccess: ${e.message}")
    }
}

fun deleteFile(file: UploadDto) {
    try {
        CoroutineScope(Dispatchers.Main).launch {

            XMLHttpRequest().apply {
                open("DELETE", "$BASE_SERVER/upload")
                setRequestHeader("Content-Type", "application/json")
                onload = { requestStatus.value = status.toInt()
                    deleteFileS3(status.toInt(),file)
                }

                onerror = { requestStatus.value = status.toInt() }

                send(Json.encodeToString(file))
            }
        }
    } catch (e: Exception) {
        println("Error in deleteAccess: ${e.message}")
    }
}

private fun deleteFileS3(returnStatus: Int, file: UploadDto) {
    if (returnStatus == 200) {
        try {
            XMLHttpRequest().apply {
                open(
                    "DELETE",
                    "https://uploads-q1w2e3r4-s3.$BASE_S3/uploads/${file.classCode}/${file.fileCode}"
                )
                setRequestHeader("Content-Type", "application/json")

                onload = { requestStatus.value = status.toInt()

                println("retorno file: $statusText ")
                }

                onerror = { requestStatus.value = status.toInt()
                    println("retorno file: $statusText ")
                }

                send(file.fileCode)
                fileSelected.value = null
            }
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }
}