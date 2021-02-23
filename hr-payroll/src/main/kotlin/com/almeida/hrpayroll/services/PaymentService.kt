package com.almeida.hrpayroll.services

import com.almeida.hrpayroll.entities.Payment
import com.almeida.hrpayroll.feignclients.WorkerFeignClient
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class PaymentService(val workerFeignClient: WorkerFeignClient) {
    fun getPayment(workerId: Long, days: Int) : Payment {
        val worker = workerFeignClient.findById(workerId).body ?: throw Exception()
        return Payment(worker.name, worker.dailyIncome, days)
    }
}