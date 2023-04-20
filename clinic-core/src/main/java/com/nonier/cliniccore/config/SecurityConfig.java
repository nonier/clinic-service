package com.nonier.cliniccore.config;

import com.nonier.cliniccore.jwt.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors()
                .and()
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests(req ->
                        req.requestMatchers("/login").permitAll()
                                .requestMatchers("/register").permitAll()
                                .requestMatchers(HttpMethod.GET, "/doctors/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/specializations/**").permitAll()
                                .requestMatchers("/consultations/**").authenticated()
                                .requestMatchers("/users/**").hasAuthority("ADMIN")
                                .requestMatchers("/role/**").hasAuthority("ADMIN")
                                .requestMatchers("/messages/**").hasAnyAuthority("ADMIN", "DOCTOR")
                                .requestMatchers("/dialogs/**").hasAnyAuthority("ADMIN", "DOCTOR")
                                .requestMatchers("/reviews/**").hasAnyAuthority("ADMIN", "DOCTOR")
                                .requestMatchers("/actuator/**").authenticated()
                                .anyRequest().denyAll())
                .addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource()
    {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept","Authorization"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}