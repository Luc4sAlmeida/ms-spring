package com.almeida.hrworker.resources

import com.almeida.hrworker.entities.Worker
import com.almeida.hrworker.repositories.WorkerRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RefreshScope
@RestController
@RequestMapping("/workers")
class WorkerResource(val workerRepository: WorkerRepository) {

    companion object {
        val logger: org.slf4j.Logger = LoggerFactory.getLogger(WorkerResource::class.java)
    }

    @Value("\${test.config}")
    lateinit var test: String

    @Autowired
    lateinit var environment: Environment

    @GetMapping("/configs")
    fun findConfig(): ResponseEntity<String> {
        logger.info(test)
        return ResponseEntity.noContent().build()
    }

    @GetMapping
    fun findAll(): ResponseEntity<List<Worker>> {
        return ResponseEntity(
            workerRepository.findAll(),
            HttpStatus.FOUND
        )
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<Worker> {
        logger.info("PORT = ${environment.getProperty("local.server.port")}")
        val worker = workerRepository.findById(id).get()

        return ResponseEntity(
            worker,
            HttpStatus.OK
        )
    }
}