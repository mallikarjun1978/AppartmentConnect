package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import java.util.*;

@Configuration
public class SpringSecurityConfig {

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        System.out.println("IN FILTER CHAIN...");

        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/front-end/admin/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers("/front-end/residents/register").permitAll()  // ✅ Allow public access to registration
                .requestMatchers("/front-end/residents/addResident").permitAll()  // ✅ Allow public access to registration
                .requestMatchers("/front-end/**").hasAuthority("ROLE_RESIDENTS")
                .requestMatchers("/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/front-end/residents/login")
                .loginProcessingUrl("/login")
                .successHandler((request, response, authentication) -> {
                    // Redirect based on role
                    String role = authentication.getAuthorities().iterator().next().getAuthority();
                    if (role.equals("ROLE_ADMIN")) {
                        response.sendRedirect("/front-end/admin/adminhome");
                    } else if (role.equals("ROLE_RESIDENTS")) {
                        response.sendRedirect("/front-end/residents/residenthome");
                    } else {
                        response.sendRedirect("/"); // Default redirect
                    }
                })
               .permitAll()
            )
            .logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .permitAll()
            );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
