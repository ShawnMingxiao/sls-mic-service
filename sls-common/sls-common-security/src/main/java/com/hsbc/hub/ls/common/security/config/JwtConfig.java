/*
 * @Copyright. HSBC HOLDINGS PLC 2020. ALL RIGHTS RESERVED.
 * This software is only to be used for the purpose for which it has been provided.
 * No part of it is to be reproduced disassembled, transmitted, stored in a retrieval
 * system nor translated in any human or computer language in any way or for any
 * other purpose whatsoever without the prior written consent of HSBC Holdings plc.
 */

package com.hsbc.hub.ls.common.security.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

/**
 * @Author: Shawn Li
 * @Create: 2020/03/24
 * @Description: JWT Token
 */

@Configuration
public class JwtConfig {

    public static final String public_cert = "public.cert";

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Bean
    @Qualifier("tokenStore")
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter);
    }

    @Bean
    protected JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        Resource resource =  new ClassPathResource(public_cert);

        String publicKey;
        try {
            publicKey = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()));
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

        converter.setVerifierKey(publicKey);
        return converter;
    }
}