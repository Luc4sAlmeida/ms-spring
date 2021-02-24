package com.almeida.hroauth

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@EnableEurekaClient
@SpringBootApplication
class HrOauthApplication

fun main(args: Array<String>) {
	runApplication<HrOauthApplication>(*args)
}
