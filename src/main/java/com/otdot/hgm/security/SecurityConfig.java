package com.otdot.hgm.security;

import com.otdot.hgm.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.security.SecureRandom;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    private static final int PW_ENCODER_STRENGTH = 16;
    @Autowired
    private final UserDetailsServiceImpl userDetailsService;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/auth/login", "/graphql").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .securityContext(securityContext -> securityContext.requireExplicitSave(false)) // Pyynnöt React native apistä ei onnistuneet ilman tätä tuli "Http403ForbiddenEntryPoint 403 Forbbiden: Pre-authenticated entry point called."
                .authenticationProvider(daoAuthenticationProvider())
                .addFilterBefore(authTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() throws Exception {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(PW_ENCODER_STRENGTH, new SecureRandom());
    }


    @Bean
    public AuthTokenFilter authTokenFilter() {
        return new AuthTokenFilter();
    }


//    @Bean
//    public static InMemoryUserDetailsManager userDetailsService() {
//        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();
//        UserDetails rob = userBuilder.username("rob").password("rob").roles("USER").build();
//        UserDetails admin = userBuilder.username("admin").password("admin").roles("USER", "ADMIN").build();
//        return new InMemoryUserDetailsManager(rob, admin);
//    }
}
