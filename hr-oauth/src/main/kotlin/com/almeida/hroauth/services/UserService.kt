package com.almeida.hroauth.services

import com.almeida.hroauth.entities.UserEntity
import com.almeida.hroauth.feignclients.UserFeignClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class UserService {
    @Autowired
    lateinit var userFeignClient: UserFeignClient

    fun findByEmail(email: String): UserEntity? {
        return userFeignClient.findByEmail(email).body ?: throw RuntimeException("User not found")
    }
}