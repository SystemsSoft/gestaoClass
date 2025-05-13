package org.gestao.model

import kotlinx.serialization.Serializable


@Serializable
 class User {
    var id = 0
    var name = ""
    var company = ""
    var password  = ""
 }
