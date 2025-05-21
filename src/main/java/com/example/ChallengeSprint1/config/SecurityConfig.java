package com.example.ChallengeSprint1.config;

import com.example.ChallengeSprint1.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(prePostEnabled = true) 
public class SecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/css/**", "/js/**", "/img/**", "/public/**").permitAll()
                        .requestMatchers("/api/v1/admin/**").hasRole("ADMIN") // URLs protegidas por role ADMIN
                        .requestMatchers("/api/v1/user/**").hasAnyRole("USER", "ADMIN") // USER e ADMIN acessam
                        .anyRequest().authenticated() // Todo o resto precisa estar autenticado
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/consulta/lista", true) // Redireciona após login
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout") // Redireciona após logout
                        .permitAll()
                )
                .csrf(csrf -> csrf.disable()); // ⚠️ Desativado apenas para facilitar testes locais

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Criptografa senhas no login
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager(); // Autenticação baseada no UserDetailsService
    }
}
