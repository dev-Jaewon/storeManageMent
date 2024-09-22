package com.won.StoreManageMent.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import com.won.StoreManageMent.common.jwt.JwtProvider;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@Aspect
@Component
@AllArgsConstructor
public class AuthAspect {
    
    public final JwtProvider jwtProvider;
    private final HttpServletRequest request;

    @Before("@annotation(Auth)")
    public void checkAuth(){
        String Authorization = request.getHeader("Authorization");

        if (Authorization == null || !Authorization.startsWith("Bearer ")) {
            throw new AccessDeniedException("접근권한이 없습니다.");
        }

        String token = Authorization.substring(7);
        String resultCheck = jwtProvider.checkToken(token);

        if (resultCheck == null) {
            throw new AccessDeniedException("접근권한이 없습니다.");
        }
    }
}
