package com.showing.backend.config;

import com.showing.backend.common.auth.JwtAuthenticationFilter;
import com.showing.backend.common.auth.JwtTokenProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final JwtTokenProvider tokenProvider;

    public JwtSecurityConfig(JwtTokenProvider tokenProvider){
        this.tokenProvider = tokenProvider;
    }

    @Override
    public void configure(HttpSecurity http) {
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(tokenProvider);
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }
}
