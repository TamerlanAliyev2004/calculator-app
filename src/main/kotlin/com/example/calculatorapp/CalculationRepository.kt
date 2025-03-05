package com.example.calculatorapp

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CalculationRepository : JpaRepository<Calculation, Long> {
    // Здесь можно добавить дополнительные методы, если нужно
}
