package com.StageArabSoft.OCR.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Configuration
@EnableWebSecurity
@Component
public class SecurityConfig {

    @SuppressWarnings("removal")
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() 
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/upload").permitAll() 
                .anyRequest().permitAll() 
            )
            .formLogin(); 
        return http.build();
    }
    
    
}
