/*
 * @Copyright. HSBC HOLDINGS PLC 2020. ALL RIGHTS RESERVED.
 * This software is only to be used for the purpose for which it has been provided.
 * No part of it is to be reproduced disassembled, transmitted, stored in a retrieval
 * system nor translated in any human or computer language in any way or for any
 * other purpose whatsoever without the prior written consent of HSBC Holdings plc.
 */

package com.hsbc.hub.ls.common.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: Shawn Li
 * @Create: 2020/03/19
 * @Description: Web MVC Config
 */
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private WebApplicationContext applicationContext;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        GlobalInterceptor interceptor = new GlobalInterceptor();
        interceptor.setRedisUUID(redisUUID);
        interceptor.setAuthIgnoreConfig(applicationContext.getBean(AuthIgnoreConfig.class));
        registry.addInterceptor(interceptor).addPathPatterns("/**");
    }

}
