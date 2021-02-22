package com.almeida.hrpayroll.services

import com.almeida.hrpayroll.entities.Payment
import com.almeida.hrpayroll.entities.Worker
import com.almeida.hrpayroll.feignclients.WorkerFeignClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.lang.Exception

@Service
class PaymentService(val workerFeignClient: WorkerFeignClient) {
    fun getPayment(workerId: Long, days: Int) : Payment {
        val worker = workerFeignClient.findById(workerId).body ?: throw Exception()
        return Payment(worker.name, worker.dailyIncome, days)
    }
}