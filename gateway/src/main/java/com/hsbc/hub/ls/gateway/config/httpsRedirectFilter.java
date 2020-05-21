/*
 * @Copyright. HSBC HOLDINGS PLC 2020. ALL RIGHTS RESERVED.
 * This software is only to be used for the purpose for which it has been provided.
 * No part of it is to be reproduced disassembled, transmitted, stored in a retrieval
 * system nor translated in any human or computer language in any way or for any
 * other purpose whatsoever without the prior written consent of HSBC Holdings plc.
 */

package com.hsbc.hub.ls.gateway.config;

import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @Author: Shawn Li
 * @Create: 2020/04/19
 * @Description:
 */
@Configuration
public class httpsRedirectFilter {
    @PostConstruct
    public void startRedirectServer() {
        NettyReactiveWebServerFactory httpNettyReactiveWebServerFactory = new NettyReactiveWebServerFactory(8080);
        httpNettyReactiveWebServerFactory.getWebServer((request, response) -> {
            URI uri = request.getURI();
            URI httpsUri;
            try {
                httpsUri = new URI("https", uri.getUserInfo(), uri.getHost(), 8443, uri.getPath(), uri.getQuery(), uri.getFragment());
            } catch (URISyntaxException e) {
                return Mono.error(e);
            }
            response.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
            response.getHeaders().setLocation(httpsUri);
            return response.setComplete();
        }).start();
    }
}
