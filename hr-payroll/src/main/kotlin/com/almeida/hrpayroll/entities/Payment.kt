package com.almeida.hrpayroll.entities

import java.io.Serializable

data class Payment(
    var name: String,
    var dailyIncome: Double,
    var days: Int
) : Serializable
{
    fun getTotal() : Double = days * dailyIncome
}