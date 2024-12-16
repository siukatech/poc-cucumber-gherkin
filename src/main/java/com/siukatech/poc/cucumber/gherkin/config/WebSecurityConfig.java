package com.siukatech.poc.cucumber.gherkin.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.cors(httpSecurityCorsConfigurer -> Customizer.withDefaults());
        http.csrf(csrfConfigurer -> csrfConfigurer.disable());

        http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                authorizationManagerRequestMatcherRegistry
                        .requestMatchers("/", "/login", "/error")
                        .permitAll()
                        .anyRequest()
//                        .fullyAuthenticated()
                        .authenticated()
        );

        http.logout(httpSecurityLogoutConfigurer -> Customizer.withDefaults());

        http.oauth2ResourceServer(httpSecurityOAuth2ResourceServerConfigurer ->
                httpSecurityOAuth2ResourceServerConfigurer
                        .jwt(jwtConfigurer ->
                                jwtConfigurer
//                                        .jwkSetUri("")
                                        .jwtAuthenticationConverter(new JwtAuthenticationConverter())
                        )
        );

        return http.build();
    }

}
