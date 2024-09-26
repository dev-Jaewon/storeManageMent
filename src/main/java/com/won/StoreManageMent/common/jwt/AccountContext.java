package com.won.StoreManageMent.common.jwt;

import com.won.StoreManageMent.auth.entity.AccountEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@RequestScope
@Component
@Getter
@Setter
public class AccountContext {
    private AccountEntity account;

    public void removeAccount(){
        this.account = null;
    }
}
