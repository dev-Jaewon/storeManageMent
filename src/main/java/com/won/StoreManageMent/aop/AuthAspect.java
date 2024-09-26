package com.won.StoreManageMent.aop;

import com.won.StoreManageMent.auth.entity.AccountEntity;
import com.won.StoreManageMent.auth.repository.AccountRepository;
import com.won.StoreManageMent.common.jwt.AccountContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountContext accountContext;

    @Around("@annotation(Auth)")
    public Object checkAuth(ProceedingJoinPoint joinPoint) throws Throwable{
        String Authorization = request.getHeader("Authorization");

        if (Authorization == null || !Authorization.startsWith("Bearer ")) {
            throw new AccessDeniedException("접근권한이 없습니다.");
        }

        String token = Authorization.substring(7);
        Long accountId = jwtProvider.checkToken(token);

        if (accountId == null) {
            throw new AccessDeniedException("접근권한이 없습니다.");
        }

        AccountEntity account = accountRepository.findById(accountId)
                .orElse(null);

        if (account == null){
            throw new AccessDeniedException("접근권한이 없습니다.");
        }

        // accessToken 계정 로컬 쓰레드 등록
        accountContext.setAccount(account);

        // API 함수 실행
        Object excuteFunction = joinPoint.proceed();

        // accessToken 계정 로컬 쓰레드 클리어
        accountContext.removeAccount();

        return excuteFunction;
    }
}
