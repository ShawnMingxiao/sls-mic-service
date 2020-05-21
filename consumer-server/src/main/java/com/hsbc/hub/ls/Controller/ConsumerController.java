/*
 * @Copyright. HSBC HOLDINGS PLC 2020. ALL RIGHTS RESERVED.
 * This software is only to be used for the purpose for which it has been provided.
 * No part of it is to be reproduced disassembled, transmitted, stored in a retrieval
 * system nor translated in any human or computer language in any way or for any
 * other purpose whatsoever without the prior written consent of HSBC Holdings plc.
 */

package com.hsbc.hub.ls.Controller;

import com.hsbc.hub.ls.service.impl.ConsumerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Shawn Li
 * @Create: 2020/04/19
 * @Description:
 */
@RestController
@RequestMapping(path = "/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerServiceImpl consumerService;

    @GetMapping(value = "/hello")
    @PreAuthorize("#oauth2.hasScope('server')")
    public String hello(@RequestParam String name){
        return "this is consumer\n " + consumerService.productHello(name);
    }
}
