package com.example.calculatorapp.service

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets
import java.util.*

@Service
class JwtTokenService {

    private val secretKey = "MySecretKeyForJWTMySecretKeyForJWT" // Храните в переменной окружения или конфиге
    private val expirationMs = 3600000 // 1 час

    // Генерация JWT токена
    fun generateToken(username: String): String {
        val now = Date()
        val expiryDate = Date(now.time + expirationMs)

        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(Keys.hmacShaKeyFor(secretKey.toByteArray(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
            .compact()
    }

    // Проверка валидности токена
    fun validateToken(token: String): Boolean {
        try {
            Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.toByteArray(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token)
            return true
        } catch (e: Exception) {
            return false
        }
    }
}
