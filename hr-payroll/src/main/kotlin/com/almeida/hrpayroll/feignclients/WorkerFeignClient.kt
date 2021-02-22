package com.almeida.hrpayroll.feignclients

import com.almeida.hrpayroll.entities.Worker
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Component
@FeignClient(name = "wr-worker", url = "localhost:8001", path = "/workers")
interface WorkerFeignClient {
    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<Worker>
}