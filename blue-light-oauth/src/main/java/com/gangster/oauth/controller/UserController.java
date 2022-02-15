package com.gangster.oauth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author blue-light
 * @date 2022-02-15
 * @description
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/getUser")
    public Object getUser(Authentication authentication){
        return authentication.getPrincipal();
    }
}
