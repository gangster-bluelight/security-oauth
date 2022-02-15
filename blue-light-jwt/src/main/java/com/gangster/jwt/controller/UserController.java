package com.gangster.jwt.controller;

import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

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

    /**
     * 解析Authorization头中存储的JWT内容
     * @param authentication
     * @param request
     * @return
     */
    @GetMapping("/getAuth")
    public Object getCurrentUser(Authentication authentication, HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        String token = header.substring(header.indexOf("bearer") + 7);
        return Jwts.parser()
                .setSigningKey("test_key".getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
    }
}
