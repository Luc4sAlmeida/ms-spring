package com.almeida.hroauth.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore

@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfig : AuthorizationServerConfigurerAdapter() {

    @Autowired
    lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder

    @Autowired
    lateinit var jwtAccessTokenConverter: JwtAccessTokenConverter

    @Autowired
    lateinit var tokenStore: JwtTokenStore

    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @Value("\${oauth.client.name}")
    lateinit var clientName: String

    @Value("\${oauth.client.secret}")
    lateinit var clientSecret: String


    override fun configure(security: AuthorizationServerSecurityConfigurer?) {
        security!!
            .tokenKeyAccess("permitAll()")
            .checkTokenAccess("isAuthenticated()")
    }

    override fun configure(clients: ClientDetailsServiceConfigurer?) {
        println(clientName)
        println(clientSecret)
        clients!!
            .inMemory()
            .withClient(clientName)
            .secret(bCryptPasswordEncoder.encode(clientSecret))
            .scopes("read", "write")
            .authorizedGrantTypes("password")
            .accessTokenValiditySeconds(86400)
    }

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer?) {
        endpoints!!
            .authenticationManager(authenticationManager)
            .tokenStore(tokenStore)
            .accessTokenConverter(jwtAccessTokenConverter)
    }
}