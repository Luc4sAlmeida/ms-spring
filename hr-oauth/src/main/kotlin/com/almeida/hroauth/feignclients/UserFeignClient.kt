package com.almeida.hroauth.feignclients

import com.almeida.hroauth.entities.UserEntity
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Component
@FeignClient(name = "hr-user", path="/users")
interface UserFeignClient {
    @GetMapping("/search")
    fun findByEmail(@RequestParam email: String): ResponseEntity<UserEntity>
}