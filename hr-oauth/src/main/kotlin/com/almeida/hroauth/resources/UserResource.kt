package com.almeida.hroauth.resources

import com.almeida.hroauth.entities.UserEntity
import com.almeida.hroauth.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserResource {

    @Autowired
    lateinit var userService: UserService

    @GetMapping("/search")
    fun findByEmail(email: String) : ResponseEntity<UserEntity> {
        val user  = userService.findByEmail(email)
        return ResponseEntity(user, HttpStatus.OK)
    }
}