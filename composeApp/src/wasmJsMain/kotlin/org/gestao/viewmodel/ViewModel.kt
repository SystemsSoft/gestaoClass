package org.gestao.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import model.Access
import model.AccessDto
import model.AccessListDto
import org.gestao.model.ClassDto
import org.gestao.model.ClassList
import org.gestao.model.ClassListDto
import org.gestao.model.UploadDto
import org.gestao.model.UploadList
import org.gestao.model.UploadsListDto
import org.gestao.model.User
import org.gestao.networking.fetchAllAccesses
import org.gestao.networking.fetchAllClasses
import org.gestao.networking.updateAccess
import org.gestao.networking.updateClasses
import org.gestao.networking.registerAccess
import org.gestao.networking.registerClass
import org.gestao.networking.registerUpload
import org.gestao.networking.deleteAccess
import org.gestao.networking.deleteClasses
import org.gestao.networking.deleteFile
import org.gestao.networking.fetchAllUploads
import org.gestao.networking.setUserValidate
import org.gestao.networking.updateFile
import org.gestao.view.isLoading
import org.gestao.view.menu.menuScreen
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

var isUserValidated = MutableStateFlow(false)
var authenticationFailed = MutableStateFlow(false)
var requestStatus = MutableStateFlow(0)
var accessListDto = MutableStateFlow(AccessListDto())
var classListDto = MutableStateFlow(ClassListDto())

var uploadListDto = MutableStateFlow(UploadsListDto())
val selectedCode = MutableStateFlow("")
val selectedClassName = MutableStateFlow("")
var allAccesses = MutableStateFlow<MutableList<AccessDto>>(mutableListOf())
var allClasses = MutableStateFlow<MutableList<ClassDto>>(mutableListOf())
var allUploads = MutableStateFlow<MutableList<UploadDto>>(mutableListOf())


fun clearAccessDto() {
accessListDto.value.classCode = ""
accessListDto.value.name = ""
accessListDto.value.className = ""
accessListDto.value.password = ""
accessListDto.value.email = ""
}

fun clearClassDto() {
    classListDto.value.className = ""
    classListDto.value.classCode = ""
}

fun clearUploadDto() {
    uploadListDto.value.fileName = ""
    uploadListDto.value.classCode = ""
}
fun getAllAccesses() {
    CoroutineScope(Dispatchers.Default).launch {
        allAccesses.value = fetchAllAccesses()
    }
}

fun getAllClasses() {
    CoroutineScope(Dispatchers.Default).launch {
        allClasses.value = fetchAllClasses()
    }
}

fun getAllUploads() {
    CoroutineScope(Dispatchers.Default).launch {
        allUploads.value = fetchAllUploads()
    }
}

fun validateUser(username: String, password: String) {
    setUserValidate(User(username, password))
}

fun bindUploadRegistration() {
    CoroutineScope(Dispatchers.Default).launch {
        registerUpload(convertUploadListDtoToUploadList())
    }
}

fun bindAccessRegistration() {
    CoroutineScope(Dispatchers.Main).launch {
        registerAccess(convertDtoToAccessList())
        isLoading.value = true
    }
}

fun bindClassRegistration() {
    CoroutineScope(Dispatchers.Main).launch {
        registerClass(convertDtoToClassesList())
        isLoading.value = true
    }
}
fun bindUpdateAccess() {
    CoroutineScope(Dispatchers.Main).launch {
        updateAccess(convertDtoToAccessDto())
        isLoading.value = true
    }
}

fun bindUpdateClass() {
    CoroutineScope(Dispatchers.Main).launch {
        updateClasses(convertDtoToClassDto())
        isLoading.value = true
    }
}

fun bindUpdateFile() {
    CoroutineScope(Dispatchers.Main).launch {
        updateFile(convertUploadToUploadDTO())
        isLoading.value = true
    }
}

fun bindDeleteFile() {
    CoroutineScope(Dispatchers.Main).launch {
        deleteFile(convertUploadToUploadDTO())
        isLoading.value = true
    }
}

fun bindDeleteAccess() {
    CoroutineScope(Dispatchers.Main).launch {
        deleteAccess(convertDtoToAccessDto())
        isLoading.value = true
    }
}

fun bindDeleteClass() {
    CoroutineScope(Dispatchers.Main).launch {
        deleteClasses(convertDtoToClassDto())
        isLoading.value = true
    }
}

fun convertDtoToAccessList(): Access {
    return Access(
        classCode = selectedCode.value,
        className = selectedClassName.value,
        password = accessListDto.value.password,
        name = accessListDto.value.name,
        email = accessListDto.value.email,
    )
}

@OptIn(ExperimentalUuidApi::class)
fun convertUploadListDtoToUploadList(): UploadList {
    return UploadList(
        fileName = uploadListDto.value.fileName,
        fileCode = Uuid.random().toString(),
        classCode = selectedCode.value,
        fileType = uploadListDto.value.fileType
    )
}

fun convertUploadToUploadDTO(): UploadDto {
    return UploadDto(
        id = uploadListDto.value.id,
        fileName = uploadListDto.value.fileName,
        fileCode = uploadListDto.value.fileCode,
        classCode = uploadListDto.value.classCode,
        fileType = uploadListDto.value.fileType
    )
}

fun convertDtoToAccessDto(): AccessDto {
    return AccessDto(
        id = accessListDto.value.id.toInt(),
        classCode = accessListDto.value.classCode,
        className = accessListDto.value.className,
        password = accessListDto.value.password,
        name = accessListDto.value.name,
        email = accessListDto.value.email,
    )
}
fun convertDtoToClassDto(): ClassDto {
    return ClassDto(
        id = classListDto.value.id.toInt(),
        classCode = classListDto.value.classCode,
        className = classListDto.value.className,
    )
}
@OptIn(ExperimentalUuidApi::class)
fun convertDtoToClassesList(): ClassList {
    return ClassList().apply {
        classCode = Uuid.random().toString()
        className = classListDto.value.className
    }
}




