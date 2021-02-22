package com.almeida.hrpayroll.resources

import com.almeida.hrpayroll.entities.Payment
import com.almeida.hrpayroll.services.PaymentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/payments")
class PaymentResource(val paymentService: PaymentService) {

    @GetMapping("/{workerId}/days/{days}")
    fun getPayment(@PathVariable workerId: Long, @PathVariable days: Int): ResponseEntity<Payment> {
        return ResponseEntity(
            paymentService.getPayment(workerId, days),
            HttpStatus.OK
        )
    }
}