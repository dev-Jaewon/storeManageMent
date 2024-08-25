package com.won.StoreManageMent.coupang.dto;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
public class CoupangCategoryDto {
    private String code;
    private String message;
    private CategoryData data;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class CategoryData {
        private Boolean isAllowSingleItem;
        private ArrayList<Attributes> attributes;
        private ArrayList<NoticeCategories> noticeCategories;
        private ArrayList<RequiredDocumentNames> requiredDocumentNames;
        private ArrayList<Certifications> certifications;
        private ArrayList<String> allowedOfferConditions;
        private Boolean isExpirationDateRequiredForRocketGrowth;

        @Getter
        @Setter
        public static class Certifications {
            private String certificationType;
            private String name;
            private String dataType;
            private String required;
            @JsonProperty("verification_OMIT_LIST")
            private ArrayList<String> verificationOmitList;
        }

        @Getter
        @Setter
        public static class RequiredDocumentNames {
            private String templateName;
            private String required;
        }

        @Getter
        @Setter
        public static class NoticeCategories {
            private String noticeCategoryName;
            private ArrayList<NoticeCategoryDetail> noticeCategoryDetailNames;
            private String required;

            @Getter
            @Setter
            public static class NoticeCategoryDetail {
                private String noticeCategoryDetailName;
                private String required;
            }
        }

        @Getter
        @Setter
        public static class Attributes {
            private String attributeTypeName;
            private String dataType;
            private String basicUnit;
            private ArrayList<String> usableUnits;
            private String required;
            private String groupNumber;
            private String exposed;
        }
    }
}
