package com.almeida.hroauth.services

import com.almeida.hroauth.feignclients.UserFeignClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserService : UserDetailsService {

    @Autowired
    lateinit var userFeignClient: UserFeignClient

    override fun loadUserByUsername(email: String?): UserDetails {
        return userFeignClient.findByEmail(email!!).body ?: throw UsernameNotFoundException("User not found")
    }
}