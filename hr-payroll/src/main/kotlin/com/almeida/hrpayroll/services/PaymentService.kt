package com.almeida.hrpayroll.services

import com.almeida.hrpayroll.entities.Payment
import com.almeida.hrpayroll.entities.Worker
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.lang.Exception

@Service
class PaymentService(val restTemplate: RestTemplate) {

    @Value("\${hr-worker.host}")
    lateinit var workerHost : String

    fun getPayment(workerId: Long, days: Int) : Payment {
        val params = mapOf("id" to workerId.toString())
        val worker = restTemplate.getForObject("$workerHost/workers/{id}", Worker::class.java, params) ?: throw Exception()
        return Payment(worker.name, worker.dailyIncome, days)
    }
}