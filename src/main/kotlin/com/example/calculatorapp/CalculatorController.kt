package com.example.calculatorapp

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.security.core.annotation.AuthenticationPrincipal
import com.example.calculatorapp.service.JwtTokenService
import org.springframework.web.bind.annotation.*
import org.springframework.security.core.Authentication

@RestController
@RequestMapping("/api/calculator")
class CalculatorController {

    @Autowired
    private lateinit var calculationRepository: CalculationRepository

    // Получаем имя пользователя из аутентификации
    @GetMapping("/add")
    fun add(@AuthenticationPrincipal user: String?, @RequestParam(value = "num1") num1: Double, @RequestParam(value = "num2") num2: Double): Double {
        val result = num1 + num2
        // Сохраняем в базе данных
        calculationRepository.save(Calculation(num1 = num1, num2 = num2, operation = "add", result = result))
        return result
    }

    @GetMapping("/subtract")
    fun subtract(@AuthenticationPrincipal user: String?, @RequestParam(value = "num1") num1: Double, @RequestParam(value = "num2") num2: Double): Double {
        val result = num1 - num2
        // Сохраняем в базе данных
        calculationRepository.save(Calculation(num1 = num1, num2 = num2, operation = "subtract", result = result))
        return result
    }

    @GetMapping("/multiply")
    fun multiply(@AuthenticationPrincipal user: String?, @RequestParam(value = "num1") num1: Double, @RequestParam(value = "num2") num2: Double): Double {
        val result = num1 * num2
        // Сохраняем в базе данных
        calculationRepository.save(Calculation(num1 = num1, num2 = num2, operation = "multiply", result = result))
        return result
    }

    @GetMapping("/divide")
    fun divide(@AuthenticationPrincipal user: String?, @RequestParam(value = "num1") num1: Double, @RequestParam(value = "num2") num2: Double): Double {
        if (num2 == 0.0) {
            throw ArithmeticException("Cannot divide by zero")
        }
        val result = num1 / num2
        // Сохраняем в базе данных
        calculationRepository.save(Calculation(num1 = num1, num2 = num2, operation = "divide", result = result))
        return result
    }
}
