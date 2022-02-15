package com.gangster.oauth_redis.config.auth;

import com.gangster.oauth_redis.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.annotation.Resource;

/**
 * @author blue-light
 * @date 2022-02-14
 * @description @EnableAuthorizationServer // 开启授权服务器
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Resource
    private PasswordEncoder passwordEncoder;

    /**
     * 用于配置令牌断点的安全约束
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        super.configure(security);
    }

    /**
     * 用于配置客户端详情服务 ClientDetailsService，客户端详情信息在此初始化，能够将客户端详情信息从数据库中查询在在此配置
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                // 客户端ID
                .withClient("client")
                // 密钥
                .secret(passwordEncoder.encode("112233"))
                // 回调地址
                .redirectUris("http://www.baidu.com")
                // 授权范围
                .scopes("all")
                /*
                 * 授权类型
                 * authorization_code:授权码模式
                 */
                .authorizedGrantTypes("authorization_code","password");

    }

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private UserService userService;

    @Resource
    @Qualifier("redisTokenStore")
    private TokenStore redisTokenStore;

    /**
     * 用于配置令牌的访问断点和令牌服务
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userService)
                .tokenStore(redisTokenStore);
    }
}
