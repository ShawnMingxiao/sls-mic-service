/*
 * @Copyright. HSBC HOLDINGS PLC 2020. ALL RIGHTS RESERVED.
 * This software is only to be used for the purpose for which it has been provided.
 * No part of it is to be reproduced disassembled, transmitted, stored in a retrieval
 * system nor translated in any human or computer language in any way or for any
 * other purpose whatsoever without the prior written consent of HSBC Holdings plc.
 */

package com.hsbc.hub.ls;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: Shawn Li
 * @Create: 2020/04/19
 * @Description:
 */
@SpringCloudApplication
@EnableFeignClients
public class ConsumerServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerServerApplication.class, args);
    }
}
