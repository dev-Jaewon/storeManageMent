package com.won.StoreManageMent.auth.dto;

import lombok.Getter;
import lombok.Setter;

public class PlatFormInfoDto {

    @Getter
    @Setter
    private static class FlatFormAuthInfo {
        private Long accountId;
        private String flatForm;
        private String option1;
        private String option2;
    }

    public static class InsertFlatFormAuthInfo extends FlatFormAuthInfo{}

    @Getter
    @Setter
    public static class UpdateFlatFormAuthInfo extends FlatFormAuthInfo {
        private Long id;
    }
}
