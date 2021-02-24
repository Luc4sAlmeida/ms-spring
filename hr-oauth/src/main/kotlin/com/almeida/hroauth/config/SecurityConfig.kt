package com.almeida.hroauth.config

import com.almeida.hroauth.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder

    @Autowired
    lateinit var userService: UserService

    @Autowired
    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth!!.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder)
    }

    @Bean
    override fun authenticationManager(): AuthenticationManager {
        return super.authenticationManager()
    }
}