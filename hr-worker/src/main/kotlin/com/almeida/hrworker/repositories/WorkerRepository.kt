package com.almeida.hrworker.repositories

import com.almeida.hrworker.entities.Worker
import org.springframework.data.jpa.repository.JpaRepository

interface WorkerRepository : JpaRepository<Worker, Long>