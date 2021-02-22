package com.almeida.hrpayroll.services

import com.almeida.hrpayroll.entities.Payment
import org.springframework.stereotype.Service

@Service
class PaymentService {
    fun getPayment(id: Long, days: Int) : Payment {
        return Payment("Lucas", 100.0, days)
    }
}