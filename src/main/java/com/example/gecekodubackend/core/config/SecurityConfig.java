package com.example.gecekodubackend.core.config;

import com.example.gecekodubackend.core.security.JwtAuthFilter;
import org.springframework.http.HttpMethod;
import com.example.gecekodubackend.business.abstracts.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(JwtAuthFilter jwtAuthFilter, UserService userService, PasswordEncoder passwordEncoder) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(x ->
                        x
                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                                .requestMatchers("/webApi/auth/**").permitAll()

                                .requestMatchers("/webApi/users/addUser").permitAll()
                                .requestMatchers("/webApi/users/deleteUser").hasAnyRole("ADMIN", "MODERATOR")
                                .requestMatchers("/webApi/users/updateUser").hasAnyRole("ADMIN", "MODERATOR")
                                .requestMatchers("/webApi/users/getUserById").hasAnyRole("ADMIN", "MODERATOR")
                                .requestMatchers("/webApi/users/getUserByEmail").hasAnyRole("ADMIN", "MODERATOR")
                                .requestMatchers("/webApi/users/getAllUsers").hasAnyRole("ADMIN", "MODERATOR")
                                .requestMatchers("/webApi/users/addModerator").hasRole("ADMIN")
                                .requestMatchers("/webApi/users/removeModerator").hasRole("ADMIN")
                                .requestMatchers("/webApi/users/addUserToEvent").hasAnyRole("ADMIN", "MODERATOR")
                                .requestMatchers("/webApi/users/addUserToWorkshop").hasAnyRole("ADMIN", "MODERATOR")

                                .requestMatchers("/webApi/workshops/addWorkshop").hasAnyRole("ADMIN", "MODERATOR")
                                .requestMatchers("/webApi/workshops/deleteWorkshop").hasAnyRole("ADMIN", "MODERATOR")
                                .requestMatchers("/webApi/workshops/updateWorkshop").hasAnyRole("ADMIN", "MODERATOR")
                                .requestMatchers("/webApi/workshops/getWorkshopById").permitAll()
                                .requestMatchers("/webApi/workshops/getAllWorkshops").permitAll()
                                .requestMatchers("/webApi/workshops/getWorkshopByName").hasAnyRole("ADMIN", "MODERATOR")

                                .requestMatchers("/webApi/events/addEvent").hasAnyRole("ADMIN", "MODERATOR")
                                .requestMatchers("/webApi/events/deleteEvent").hasAnyRole("ADMIN", "MODERATOR")
                                .requestMatchers("/webApi/events/getEventById").permitAll()
                                .requestMatchers("/webApi/events/updateEvent").hasAnyRole("ADMIN", "MODERATOR")
                                .requestMatchers("/webApi/events/getAllEvents").permitAll()
                                .requestMatchers("/webApi/events/getEventByName").hasAnyRole("ADMIN", "MODERATOR")

                                .anyRequest().hasAnyRole("ADMIN")
                )
                .sessionManagement(x -> x.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}