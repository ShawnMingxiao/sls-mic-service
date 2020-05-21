/*
 * @Copyright. HSBC HOLDINGS PLC 2020. ALL RIGHTS RESERVED.
 * This software is only to be used for the purpose for which it has been provided.
 * No part of it is to be reproduced disassembled, transmitted, stored in a retrieval
 * system nor translated in any human or computer language in any way or for any
 * other purpose whatsoever without the prior written consent of HSBC Holdings plc.
 */

package com.hsbc.hub.ls.client;

import com.hsbc.hub.ls.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: Shawn Li
 * @Create: 2020/04/19
 * @Description:
 */
@FeignClient(name = "PRODUCT-SERVER", fallback = ProductServerClientFallback.class, configuration = {FeignConfig.class})
public interface ProductServerClient {

    @RequestMapping(method = RequestMethod.GET, value = "/product/hello", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String productHello(@RequestParam("name") String name);
}
