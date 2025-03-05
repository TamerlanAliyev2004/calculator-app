package com.example.calculatorapp

import jakarta.persistence.*

@Entity
@Table(name = "calculations")
data class Calculation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val num1: Double,

    @Column(nullable = false)
    val num2: Double,

    @Column(nullable = false)
    val operation: String,

    @Column(nullable = false)
    val result: Double
)
