package com.tech.tech.components;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin/**").hasRole("ADMIN") // Only ADMIN can access
                .requestMatchers("/user/**").hasRole("USER")  // Only USER can access
                .requestMatchers("/api/**").permitAll()   // Public access
                .anyRequest().authenticated() // Any other request requires authentication
            )
            .formLogin(form -> form.defaultSuccessUrl("/api", true)) // Redirect after login
            .logout(logout -> logout.logoutUrl("/logout"))
            .csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
