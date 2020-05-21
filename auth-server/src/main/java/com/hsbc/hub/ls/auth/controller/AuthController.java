/*
 * @Copyright. HSBC HOLDINGS PLC 2020. ALL RIGHTS RESERVED.
 * This software is only to be used for the purpose for which it has been provided.
 * No part of it is to be reproduced disassembled, transmitted, stored in a retrieval
 * system nor translated in any human or computer language in any way or for any
 * other purpose whatsoever without the prior written consent of HSBC Holdings plc.
 */

package com.hsbc.hub.ls.auth.controller;

import com.hsbc.hub.ls.auth.entity.User;
import com.hsbc.hub.ls.auth.service.UserService;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.KeyPair;
import java.security.Principal;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

/**
 * @Author: Shawn Li
 * @Create: 2020/03/19
 * @Description: User Controller
 */
@RestController
@RequestMapping("/oauth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private KeyPair keyPair;

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public Principal getUser(Principal principal) {
        return principal;
    }

    @PreAuthorize("#oauth2.hasScope('sls')")
    @RequestMapping(method = RequestMethod.POST)
    public void createUser(@Valid @RequestBody User user) {
        userService.create(user);
    }

    @GetMapping("/.well-know/jwks.json")
    public Map<String, Object> getKey(){
        RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();
        com.nimbusds.jose.jwk.RSAKey key = new RSAKey.Builder(publicKey).build();
        return new JWKSet(key).toJSONObject();
    }
}
