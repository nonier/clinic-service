package com.nonier.cliniccore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf()
                .disable()
                .authorizeHttpRequests( req ->
                        req.requestMatchers("/users/**").hasAuthority("ADMIN")
                                .requestMatchers("/doctors/**").hasAnyAuthority("ADMIN", "DOCTOR")
                                .requestMatchers("/role/**").hasAuthority("ADMIN")
                                .requestMatchers("/actuator/**").permitAll()
                                .requestMatchers("/login").permitAll()
                                .anyRequest().denyAll())
                .formLogin()
                .and()
                .build();
    }


}
