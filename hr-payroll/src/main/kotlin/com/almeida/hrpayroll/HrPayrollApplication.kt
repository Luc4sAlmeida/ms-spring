package com.almeida.hrpayroll

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableCircuitBreaker
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
class HrPayrollApplication

fun main(args: Array<String>) {
	runApplication<HrPayrollApplication>(*args)
}
