package com.example.calculatorapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CalculatorAppApplication

fun main(args: Array<String>) {
    runApplication<CalculatorAppApplication>(*args)
}
