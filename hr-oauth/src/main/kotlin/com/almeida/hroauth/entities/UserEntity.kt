package com.almeida.hroauth.entities

import java.io.Serializable

data class UserEntity(
    var id: Long,
    var name: String,
    var email: String,
    var password: String,
    val roles: Set<RoleEntity> = hashSetOf()
)