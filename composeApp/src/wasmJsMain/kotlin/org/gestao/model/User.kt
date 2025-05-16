package org.gestao.model

import kotlinx.serialization.Serializable


@Serializable
data class User(
    var name: String,
    var password: String
)


