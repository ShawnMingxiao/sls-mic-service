/*
 * @Copyright. HSBC HOLDINGS PLC 2020. ALL RIGHTS RESERVED.
 * This software is only to be used for the purpose for which it has been provided.
 * No part of it is to be reproduced disassembled, transmitted, stored in a retrieval
 * system nor translated in any human or computer language in any way or for any
 * other purpose whatsoever without the prior written consent of HSBC Holdings plc.
 */

package com.hsbc.hub.ls.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;


/**
 * @Author: Shawn Li
 * @Create: 2020/04/18
 * @Description:
 */

@EnableWebFluxSecurity
class SecurityConfig {

    @Bean
    SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeExchange()
                .pathMatchers(HttpMethod.POST, "/oauth/**").permitAll()
                .pathMatchers(HttpMethod.DELETE, "/posts/**").hasRole("ADMIN")
                .pathMatchers("/posts/**").authenticated()
                .anyExchange().permitAll()
                .and()
                .httpBasic()
                //.pathMatchers("/users/{user}/**").access(this::currentUserMatchesPath)

                .and()
                .build();
    }

//    private Mono<AuthorizationDecision> currentUserMatchesPath(Mono<Authentication> authentication, AuthorizationContext context) {
//        return authentication
//                .map(a -> context.getVariables().get("user").equals(a.getName()))
//                .map(granted -> new AuthorizationDecision(granted));
//    }
//
//    @Bean
//    public MapReactiveUserDetailsService userDetailsRepository() {
//        UserDetails rob = User.withDefaultPasswordEncoder().username("test").password("test123").roles("USER").build();
//        UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("admin123").roles("USER", "ADMIN").build();
//        return new MapReactiveUserDetailsService(rob, admin);
//    }
}
