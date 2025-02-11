package com.mirkwood.logistics.core.configurations;

import com.mirkwood.logistics.core.jwt.JwtAuthenticationEntryPoint;
import com.mirkwood.logistics.core.jwt.JwtAuthenticationFilter;
import com.mirkwood.logistics.core.jwt.JwtHelper;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    private final CustomStaffDetailsService customStaffDetailsService;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtHelper jwtHelper;

    @Autowired
    public SecurityConfig(CustomStaffDetailsService customStaffDetailsService, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtHelper jwtHelper) {
        this.customStaffDetailsService = customStaffDetailsService;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtHelper = jwtHelper;  // **Inject JwtHelper**
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtHelper, customStaffDetailsService, jwtAuthenticationEntryPoint);  // **Pass JwtHelper, CustomStaffDetailsService, JwtAuthenticationEntryPoint**
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/mirkwood-logistics/session/login").permitAll()
                        .requestMatchers("/mirkwood-logistics/session/logout").permitAll()
                        .requestMatchers("/mirkwood-logistics/staff/fetch/**").permitAll()
                        .requestMatchers("/mirkwood-logistics/parcel-tracking/status").permitAll()
                        .requestMatchers("/mirkwood-logistics/parcel/fetch/trackingNumber").permitAll()

                        .requestMatchers("/mirkwood-logistics/parcel/register").hasRole("STAFF")

                        .anyRequest().authenticated())
                .sessionManagement(sesssion -> sesssion.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return customStaffDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
