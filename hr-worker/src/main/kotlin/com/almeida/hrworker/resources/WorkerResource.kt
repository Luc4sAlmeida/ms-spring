package com.almeida.hrworker.resources

import com.almeida.hrworker.entities.Worker
import com.almeida.hrworker.repositories.WorkerRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/workers")
class WorkerResource(val workerRepository: WorkerRepository) {

    @GetMapping
    fun findAll(): ResponseEntity<List<Worker>> {
        return ResponseEntity(
            workerRepository.findAll(),
            HttpStatus.FOUND
        )
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<Worker> {
        val worker = workerRepository.findById(id).get()
        return ResponseEntity(
            worker,
            HttpStatus.OK
        )
    }
}