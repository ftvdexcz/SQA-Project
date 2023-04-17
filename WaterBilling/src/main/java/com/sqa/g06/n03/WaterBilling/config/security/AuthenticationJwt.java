package com.sqa.g06.n03.WaterBilling.config.security;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationJwt {
    public String getTokenFromRequest(HttpServletRequest request){
        String token = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for (Cookie cookie: cookies){
                System.out.println(cookie);
                if(cookie.getName().equals("token")){
                    token = cookie.getValue();
                    return token;
                }
            }
        }

        token = request.getHeader("Authorization");
        if(token != null && token.startsWith("Bearer")){
            token = token.split(" ")[1];
        }
//        System.out.println(token);
        return token;
    }
}
