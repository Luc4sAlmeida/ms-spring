package com.almeida.hruser.repositories

import com.almeida.hruser.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Long> {
    fun findByEmail(email: String) : UserEntity?
}