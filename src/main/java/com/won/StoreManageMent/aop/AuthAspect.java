package com.won.StoreManageMent.aop;

import com.won.StoreManageMent.auth.entity.AccountEntity;
import com.won.StoreManageMent.auth.repository.AccountRepository;
import com.won.StoreManageMent.common.jwt.AccountContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        }

        try {

            String token = Authorization.substring(7);

            long accountId = jwtProvider.checkToken(token);

            AccountEntity account = accountRepository.findById(accountId)
                    .orElse(null);

            if (account == null){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED);
            }

            // accessToken 계정 로컬 쓰레드 등록
            accountContext.setAccount(account);

            // API 함수 실행
            Object excuteFunction = joinPoint.proceed();

            // accessToken 계정 로컬 쓰레드 클리어
            accountContext.removeAccount();

            return excuteFunction;

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(401).body("인증실패하였습니다.");
        }
    }
}
