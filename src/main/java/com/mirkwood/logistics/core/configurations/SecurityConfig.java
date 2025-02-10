package com.mirkwood.logistics.core.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomStaffDetailsService customStaffDetailsService;

    @Autowired
    public SecurityConfig(CustomStaffDetailsService customStaffDetailsService) {
        this.customStaffDetailsService = customStaffDetailsService;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/mirkwood-logistics/session/login").permitAll()
                        .requestMatchers("/mirkwood-logistics/session/logout").permitAll()
                        .requestMatchers("/mirkwood-logistics/parcel-tracking/status").permitAll()
                        .requestMatchers("/mirkwood-logistics/parcel/fetch/trackingNumber").permitAll()
                        .requestMatchers("/mirkwood-logistics/parcel/**").hasRole("STAFF")
                        .anyRequest().authenticated())
                .sessionManagement(sesssion -> sesssion.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return customStaffDetailsService::loadStaffByUsername;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
