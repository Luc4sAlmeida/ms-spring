package com.almeida.hrapigatewayzuul.config


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore


@Configuration
@EnableResourceServer
class ResourceConfig : ResourceServerConfigurerAdapter() {

    companion object {
        val PUBLIC = arrayOf("/hr-oauth/oauth/token")
        val OPERATOR = arrayOf("/hr-worker/**")
        val ADMIN = arrayOf("/hr-payroll/**", "/hr-user/**")
    }

    @Autowired
    lateinit var tokenStore: JwtTokenStore

    override fun configure(resources: ResourceServerSecurityConfigurer?) {
       resources!!.tokenStore(tokenStore)
    }

    override fun configure(http: HttpSecurity?) {
        http!!
            .authorizeRequests()
            .antMatchers(*PUBLIC).permitAll()
            .antMatchers(HttpMethod.GET, *OPERATOR).hasAnyRole("OPERATOR", "ADMIN")
            .antMatchers(*ADMIN).hasRole("ADMIN")
            .anyRequest().authenticated()
    }
}