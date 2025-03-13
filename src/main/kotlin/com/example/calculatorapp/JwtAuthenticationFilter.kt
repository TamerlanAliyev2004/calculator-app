package com.example.calculatorapp.config

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.nio.charset.StandardCharsets
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@Component
class JwtAuthenticationFilter : OncePerRequestFilter() {

    // Секретный ключ для подписи токена
    private val secretKey = "MySecretKeyForJWTMySecretKeyForJWT"

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader = request.getHeader("Authorization")
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            val token = authHeader.substring(7)
            try {
                val claims = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secretKey.toByteArray(StandardCharsets.UTF_8)))
                    .build()
                    .parseClaimsJws(token)

                val username = claims.body.subject // Например, username

                // Создаем объект аутентификации
                val authToken = UsernamePasswordAuthenticationToken(username, null, emptyList())
                authToken.details = WebAuthenticationDetailsSource().buildDetails(request)

                // Устанавливаем аутентификацию в контекст
                SecurityContextHolder.getContext().authentication = authToken
            } catch (ex: Exception) {
                // Ошибка при обработке токена
                println("Invalid JWT: ${ex.message}")
            }
        }

        filterChain.doFilter(request, response)
    }
}
