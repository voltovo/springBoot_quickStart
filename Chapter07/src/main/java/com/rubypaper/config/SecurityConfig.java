package com.rubypaper.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    public void authenticate(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                .withUser("manager")
                .password("{noop}manager123")
                .roles("MANAGER");
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}admin123")
                .roles("ADMIN");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(new AntPathRequestMatcher("/"),
                                new AntPathRequestMatcher("/login"),
                                new AntPathRequestMatcher("/loginSuccess")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/member/**")).authenticated()
                        .requestMatchers(new AntPathRequestMatcher("/manager/**")).hasRole("MANAGER")
                        .requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("ADMIN"));
        http.formLogin(formLogin -> formLogin
                .loginPage("/login")
                .defaultSuccessUrl("/loginSuccess", true));
        return http.build();
    }
}
