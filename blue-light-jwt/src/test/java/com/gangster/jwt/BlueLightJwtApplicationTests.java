package com.gangster.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class BlueLightJwtApplicationTests {

    @Test
    public void testCreatTokenByClaims() {
        //当前系统时间的长整型
        long now = System.currentTimeMillis();
        //过期时间，这里是1分钟后的时间长整型
        long exp = now + 60 * 1000;
        //创建一个JwtBuilder对象
        JwtBuilder jwtBuilder = Jwts.builder()
                //声明的标识{"jti":"888"}
                .setId("888")
                //主体，用户{"sub":"Rose"}
                .setSubject("Rose")
                //创建日期{"ita":"yjxxtxx"}
                .setIssuedAt(new Date())
                //签名手段，参数1：算法，参数2：盐
                .signWith(SignatureAlgorithm.HS256, "yjxxt")
                //设置过期时间
                .setExpiration(new Date(exp))
                //直接传入map
                // .addClaims(map)
                .claim("roles","admin")
                .claim("logo","yjxxt.jpg");
        //获取jwt的token
        String token = jwtBuilder.compact();
        System.out.println(token);
    }


    @Test
    public void testParseTokenByClaims() {
        //token
        String token = "eyJhbGciOiJIUzI1NiJ9" +
                ".eyJqdGkiOiI4ODgiLCJzdWIiOiJSb3NlIiwiaWF0IjoxNTc4ODE1MDYyLCJleHAiOjE1Nzg4MTUxMjIsInJvbGVzIjoiYWRtaW4iLCJsb2dvIjoic2hzeHQuanBnIn0.hKog0RsZ9_6II_R8kUCp0HLAouUAYXAJVbz3xtLTUh4";
        //解析token获取负载中的声明对象
        Claims claims = Jwts.parser()
                .setSigningKey("yjxxt")
                .parseClaimsJws(token)
                .getBody();
        //打印声明的属性
        System.out.println("id:" + claims.getId());
        System.out.println("subject:" + claims.getSubject());
        System.out.println("issuedAt:" + claims.getIssuedAt());
        DateFormat sf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("签发时间:"+sf.format(claims.getIssuedAt()));
        System.out.println("过期时间:"+sf.format(claims.getExpiration()));
        System.out.println("当前时间:"+sf.format(new Date()));

        System.out.println("roles:"+claims.get("roles"));
        System.out.println("logo:"+claims.get("logo"));
    }
}
