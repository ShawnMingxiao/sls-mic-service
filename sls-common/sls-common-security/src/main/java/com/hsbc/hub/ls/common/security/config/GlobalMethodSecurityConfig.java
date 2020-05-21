/*
 * @Copyright. HSBC HOLDINGS PLC 2020. ALL RIGHTS RESERVED.
 * This software is only to be used for the purpose for which it has been provided.
 * No part of it is to be reproduced disassembled, transmitted, stored in a retrieval
 * system nor translated in any human or computer language in any way or for any
 * other purpose whatsoever without the prior written consent of HSBC Holdings plc.
 */

package com.hsbc.hub.ls.common.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @Author: Shawn Li
 * @Create: 2020/04/13
 * @Description:
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class GlobalMethodSecurityConfig {
}
