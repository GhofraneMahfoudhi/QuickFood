package com.example.QuickFood.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF for development/testing purposes
                .authorizeHttpRequests(auth -> auth
                        // Public endpoints that can be accessed without authentication
                        .requestMatchers("/api/utilisateur/create").permitAll()

                        // All other endpoints require authentication
                        .anyRequest().authenticated() // This should work in Spring Security 5.x and above
                )
                .httpBasic(Customizer.withDefaults()); // Enable HTTP Basic Authentication

        return http.build();
    }
}
