package com.nonier.cliniccore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeHttpRequests(req ->
                        req.requestMatchers("/doctors/**").permitAll()
                                .requestMatchers("/users/**").hasAuthority("ADMIN")
                                .requestMatchers("/role/**").hasAuthority("ADMIN")
                                .requestMatchers("/messages/**").hasAnyAuthority("ADMIN", "DOCTOR")
                                .requestMatchers("/dialogs/**").hasAnyAuthority("ADMIN", "DOCTOR")
                                .requestMatchers("/reviews/**").hasAnyAuthority("ADMIN", "DOCTOR")
                                .requestMatchers("/consultations/**").hasAnyAuthority("ADMIN", "DOCTOR")
                                .requestMatchers("/actuator/**").authenticated()
                                .requestMatchers("/login").permitAll()
                                .anyRequest().denyAll())
                .formLogin()
                .and()
                .httpBasic()
                .and()
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}