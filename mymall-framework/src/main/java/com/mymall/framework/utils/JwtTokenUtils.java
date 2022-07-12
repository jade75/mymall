package com.mymall.framework.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//@ConfigurationProperties(prefix = "token")
@Slf4j
@Component
@Data
public class JwtTokenUtils {

    @Value("${token.secret}")
    private String secret;
//    private String secret = "ji8n3439n439n43ld9ne9343fdfer49h";

    @Value("${token.header}")
    private String header;
//    private String header = "Authorization";

    @Value("${token.expireTime}")
    private Long expireTime;
//    private Long expireTime = 60L;

//    @Value("${token.refreshExpireTime}")
//    private Long refreshExpireTime;

    /**
     * get jwt token
     *
     * @param username
     * @return
     */
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", username);
        claims.put("created", new Date());
        return generateToken(claims);
    }

    public String generateToken(Map<String, Object> claims) {
        Date expireDate = new Date(new Date().getTime() + expireTime);
        return Jwts.builder()
                .setHeaderParam("type", "JWT")
                .setClaims(claims)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * refresh the token , it means extended validity
     *
     * @param token
     * @return new token
     */
    public String refreshToken(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            assert claims != null;
            claims.put("created", new Date());
            return generateToken(claims);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }


    /**
     * check token
     *
     * @param token
     * @return true or false
     */
    public boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            assert claims != null;
            return claims.getExpiration().before(new Date());
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            assert claims != null;
            return claims.getSubject();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    private Claims getClaimsFromToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public String getUsernameFromToken(HttpServletRequest request) {
        try {
//            RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
//            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

            String header = request.getHeader(this.header);
            if (StringUtils.isNotEmpty(header)) {
                return getUsernameFromToken(header);
            }else{
                return null;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }



//    public static void main(String[] args) throws InterruptedException {
//        JwtTokenUtils jwtTokenUtils = new JwtTokenUtils();
//        String admin = jwtTokenUtils.generateToken("admin");
//    }

}



















/*
//
    public void p1(String token) {
       Claims claims = getClaimsFromToken(token);
//        System.out.println("=====" + claims.get("username"));
        System.out.println("expire date  "+claims.getExpiration());
    }


    public static void main(String[] args) throws InterruptedException {
        JwtTokenService jwtTokenService = new JwtTokenService();

//        String token = "eyJhbGciOiJIUzUxMiJ9.eyJjcmVhdGVkIjoxNjUyOTY1Mzc5ODcxLCJleHAiOjE2NTI5NjUzODIsInVzZXJuYW1lIjoidGVzdCJ9.TMQXroBUvbrEX3jpSjclgl8wT9aSaIsS7_1mJKRxbIq8B7Cvbw7YrJCHoG8s6b7xqC-vVc79M--okmY58LLbbw";
        String token = jwtTokenService.generateToken("test");
        System.out.println(token);


        jwtTokenService.p1(token);
        System.out.println();
//
//        Thread.sleep(56000l);
//        System.out.println0(new Date());
        String token2 = jwtTokenService.refreshToken(token);
                System.out.println(token2);



        System.out.println(jwtTokenService.getUsernameFromToken(token));
//

        jwtTokenService.p1(token2);
        System.out.println();
        System.out.println(jwtTokenService.getUsernameFromToken(token2));

//        Thread.sleep(4000l);
//        System.out.println(jwtTokenService.isTokenExpired(token));
//        jwtTokenService.p1(token);

//        System.out.println(jwtTokenService.isTokenExpiredpired("eyJhbGciOiJIUzUxMiJ9.eyJjcmVhdGVkIjoxNjUyOTYzNDY5NDAzLCJleHAiOjE2NTI5NjM0NzIsInVzZXJuYW1lIjoidGVzdCJ9.Pf1PNDKFsl77xIfa4Bm-AJJjweRoAujAyMCMAgjLjlNJ4Agv4ijoKO8J8Cdkt69Zbq6BMSJEFA448KJ-473Mcw"));

    }
    */

