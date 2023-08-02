package com.rubypaper.board.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private SecurityUserDetailsService securityUserDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(new AntPathRequestMatcher("/"),
                                new AntPathRequestMatcher("/system/**")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/board/**")).authenticated()
                        .requestMatchers(new AntPathRequestMatcher("/admin/**")).hasRole("ADMIN"));
        // 로그인 페이지로 이동
        http.formLogin(formLogin -> formLogin
                .loginPage("/system/login")
                .defaultSuccessUrl("/board/getBoardList", true));
        // 권한 없는 페이지 접근
        http.exceptionHandling(exceptionHandling -> exceptionHandling
                .accessDeniedPage("/system/accessDenied"));
        //로그 아웃
        http.logout(logout -> logout
                .logoutUrl("/system/logout")
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/"));
        // jpa 사용자 정의
        http.userDetailsService(securityUserDetailsService);
        return http.build();
    }

    // 비밀 번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
