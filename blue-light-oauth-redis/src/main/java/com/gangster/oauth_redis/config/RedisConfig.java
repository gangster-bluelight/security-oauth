package com.gangster.oauth_redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;

/**
 * @author blue-light
 * @date 2022-02-15
 * @description
 */
@Configuration
public class RedisConfig {
    @Resource
    private RedisConnectionFactory factory;

    @Bean
    public TokenStore redisTokenStore(){
        return new RedisTokenStore(factory);
    }
}
