package com.almeida.hrapigatewayzuul.config


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.Ordered
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter


@Configuration
@EnableResourceServer
class ResourceConfig : ResourceServerConfigurerAdapter() {

    companion object {
        val PUBLIC = arrayOf("/hr-oauth/oauth/token")
        val OPERATOR = arrayOf("/hr-worker/**")
        val ADMIN = arrayOf("/hr-payroll/**", "/hr-user/**", "/actuator/**", "/wr-worker/actuator/**")
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

        http.cors().configurationSource(corsConfigurationSource())
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val config = CorsConfiguration()

        config.allowCredentials = true
        config.allowedOrigins = arrayListOf("*")
        config.allowedHeaders = arrayListOf("Authorization", "Content-Type")
        config.allowedMethods = arrayListOf("POST", "GET", "PUT", "DELETE", "PATCH")

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", config)

        return source
    }

    @Bean
    fun corsFilter() : FilterRegistrationBean<CorsFilter> {
        val bean = FilterRegistrationBean<CorsFilter>(CorsFilter(corsConfigurationSource()))
        bean.order = Ordered.HIGHEST_PRECEDENCE
        return bean
    }
}