/*
 * @Copyright. HSBC HOLDINGS PLC 2020. ALL RIGHTS RESERVED.
 * This software is only to be used for the purpose for which it has been provided.
 * No part of it is to be reproduced disassembled, transmitted, stored in a retrieval
 * system nor translated in any human or computer language in any way or for any
 * other purpose whatsoever without the prior written consent of HSBC Holdings plc.
 */

package com.hsbc.hub.ls.common.security.interceptor;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: Shawn Li
 * @Create: 2020/03/19
 * @Description: Global Interceptor
 */
public class GlobalInterceptor implements HandlerInterceptor {

    @Getter
    @Setter
    private AuthIgnoreConfig authIgnoreConfig;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
        long exist =  authIgnoreConfig.getIgnoreUrls().stream().filter(url-> url.trim().equals(request.getRequestURI())).count();
        if(exist != 0){
            return true;
        }
        String secretKey = request.getHeader(SecurityConstants.SECRET_KEY);
        if(StrUtil.isNotBlank(secretKey)){
            String key = (String) redisUUID.get(SecurityConstants.SECRET_KEY);
            if(!StrUtil.isBlank(key) && secretKey.equals(key)){
                return true;
            }
        }
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write(JSONUtil.toJsonStr(R.error("illegal request")));
        return false;
    }

}
