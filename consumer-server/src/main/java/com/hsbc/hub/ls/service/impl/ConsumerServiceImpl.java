/*
 * @Copyright. HSBC HOLDINGS PLC 2020. ALL RIGHTS RESERVED.
 * This software is only to be used for the purpose for which it has been provided.
 * No part of it is to be reproduced disassembled, transmitted, stored in a retrieval
 * system nor translated in any human or computer language in any way or for any
 * other purpose whatsoever without the prior written consent of HSBC Holdings plc.
 */

package com.hsbc.hub.ls.service.impl;

import com.hsbc.hub.ls.client.ProductServerClient;
import com.hsbc.hub.ls.service.ConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Shawn Li
 * @Create: 2020/04/19
 * @Description:
 */
@Service
@Slf4j
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private ProductServerClient productServerClient;


    @Override
    public String productHello(String name) {
        return productServerClient.productHello(name);
    }
}
