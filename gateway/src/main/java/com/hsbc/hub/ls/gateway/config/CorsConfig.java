/*
 * @Copyright. HSBC HOLDINGS PLC 2020. ALL RIGHTS RESERVED.
 * This software is only to be used for the purpose for which it has been provided.
 * No part of it is to be reproduced disassembled, transmitted, stored in a retrieval
 * system nor translated in any human or computer language in any way or for any
 * other purpose whatsoever without the prior written consent of HSBC Holdings plc.
 */

package com.hsbc.hub.ls.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * @Author: Shawn Li
 * @Create: 2020/04/18
 * @Description:
 */
@Configuration

public class CorsConfig {

 private static final String ALLOWED_HEADERS ="x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN,token,username,client";

//    private static final String ALLOWED_HEADERS = "*";//使用*号，在firefox浏览器无法跨域请求

private static final String ALLOWED_METHODS ="*";

private static final String ALLOWED_ORIGIN ="*";

private static final String ALLOWED_Expose ="*";

//    private static final String MAX_AGE = "18000L";

    private static final String MAX_AGE = "18000L";


    @Bean
    public WebFilter corsFilter() {
        return (ServerWebExchange ctx, WebFilterChain chain) -> {
            ServerHttpRequest request = ctx.getRequest();
            if (!CorsUtils.isCorsRequest(request)) {
                return chain.filter(ctx);
            }
            HttpHeaders requestHeaders = request.getHeaders();
            ServerHttpResponse response = ctx.getResponse();
            HttpMethod requestMethod = requestHeaders.getAccessControlRequestMethod();
            HttpHeaders headers = response.getHeaders();
            headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, ALLOWED_ORIGIN);
            headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, ALLOWED_HEADERS);
            if (requestMethod != null) {
                headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, ALLOWED_METHODS);
            }
            headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
            headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, ALLOWED_Expose);
            headers.add(HttpHeaders.ACCESS_CONTROL_MAX_AGE, MAX_AGE);
            if (request.getMethod() == HttpMethod.OPTIONS) {
                response.setStatusCode(HttpStatus.OK);
                return Mono.empty();
            }
            return chain.filter(ctx);
        };
    }

}