package com.gangster.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

/**
 * @author blue-light
 */
@SpringBootApplication
// 开启单点登录
@EnableOAuth2Sso
public class BlueLightClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlueLightClientApplication.class, args);
    }
}
