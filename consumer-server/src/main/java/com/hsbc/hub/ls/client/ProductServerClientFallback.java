/*
 * @Copyright. HSBC HOLDINGS PLC 2020. ALL RIGHTS RESERVED.
 * This software is only to be used for the purpose for which it has been provided.
 * No part of it is to be reproduced disassembled, transmitted, stored in a retrieval
 * system nor translated in any human or computer language in any way or for any
 * other purpose whatsoever without the prior written consent of HSBC Holdings plc.
 */

package com.hsbc.hub.ls.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: Shawn Li
 * @Create: 2020/04/19
 * @Description:
 */
@Component
@Slf4j
public class ProductServerClientFallback implements ProductServerClient{
    @Override
    public String productHello(String name) {

        log.info("Error calling product server", name);
        return "error calling product server";
    }
}
