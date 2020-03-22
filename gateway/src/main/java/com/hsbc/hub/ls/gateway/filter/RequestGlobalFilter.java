/*
 * @Copyright. HSBC HOLDINGS PLC 2020. ALL RIGHTS RESERVED.
 * This software is only to be used for the purpose for which it has been provided.
 * No part of it is to be reproduced disassembled, transmitted, stored in a retrieval
 * system nor translated in any human or computer language in any way or for any
 * other purpose whatsoever without the prior written consent of HSBC Holdings plc.
 */

package com.hsbc.hub.ls.gateway.filter;

import com.hsbc.hub.ls.common.security.constant.LSSecurityConstants;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author: Shawn Li
 * @Create: 2020/03/19
 * @Description: Global Filter to all services
 * Add security key to all request header.
 */
@Order(-999)
public class RequestGlobalFilter implements GlobalFilter{

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest().mutate()
                .headers(httpHeaders -> httpHeaders.remove(LSSecurityConstants.SECRET_KEY))
                .build();
        ServerHttpRequest newRequest = request.mutate().header(LSSecurityConstants.SECRET_KEY,LSSecurityConstants.SECRET_VALUE).build();
        return chain.filter(exchange.mutate().request(newRequest.mutate().build()).build());
    }
}
