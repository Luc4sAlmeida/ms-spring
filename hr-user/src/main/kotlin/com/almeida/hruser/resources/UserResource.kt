package com.almeida.hruser.resources

import com.almeida.hruser.entities.UserEntity
import com.almeida.hruser.repositories.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserResource(val userRepository: UserRepository) {

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<UserEntity> {
        val user = userRepository.findById(id).get()
        return ResponseEntity.ok(user)
    }

    @GetMapping("/search")
    fun findByEmail(@RequestParam email: String): ResponseEntity<UserEntity> {
        val user = userRepository.findByEmail(email)
        return ResponseEntity(
            user,
            if(user != null) HttpStatus.OK else HttpStatus.NOT_FOUND
        )
    }
}