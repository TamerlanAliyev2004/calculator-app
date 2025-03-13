package com.example.calculatorapp.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtFilter: JwtAuthenticationFilter // Добавляем JWT фильтр
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }  // Отключаем CSRF
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }  // Используем Stateless сессии
            .authorizeHttpRequests {
                it.requestMatchers("/auth/**").permitAll()  // Разрешаем доступ к /auth (логин)
                it.anyRequest().authenticated()  // Все остальные запросы требуют авторизации
            }
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter::class.java) // Добавляем фильтр

        return http.build()
    }

    @Bean
    fun authenticationManager(config: AuthenticationConfiguration): AuthenticationManager {
        return config.authenticationManager
    }
}
