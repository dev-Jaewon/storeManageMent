package com.won.StoreManageMent.auth.dto;

import lombok.Getter;
import lombok.Setter;

public class PlatFormInfoDto {

    @Getter
    @Setter
    public static class InsertFloatFormAuthKey {
        private Long accountId;
        private String flatForm;
        private String option1;
        private String option2;
    }
}
