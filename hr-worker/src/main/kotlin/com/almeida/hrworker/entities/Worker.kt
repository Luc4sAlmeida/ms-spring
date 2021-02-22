package com.almeida.hrworker.entities

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "tb_worker")
data class Worker(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,
    var name: String,
    var dailyIncome: Double
) : Serializable