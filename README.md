# security-oauth
security安全框架的学习和oauth2.0单点登录的应用（结合jwt）

依赖版本描述：

```lua
java---------------------------11
spring-boot--------------------2.6.3
spring-security----------------2.6.3
thymeleaf----------------------3.0.14.RELEASE
thymeleaf-spring-boot----------2.6.3
spring-data-jpa----------------2.6.3
lombok-------------------------1.18.22
```

模块介绍

```lua
client--------------单点登录的客户端
jwt-----------------继承jwt验证
oauth---------------OAuth2的集成(授权码模式)
oauth-password------OAuth2的继承(用户名密码模式)
oauth-redis---------OAuth2的集成redis(用户名密码模式)
security------------basic-auth
sso-----------------单点登录的认证系统
```

