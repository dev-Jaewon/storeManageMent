package com.won.StoreManageMent.naver.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NaverCategoryVo {
    private String name;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean first;
    private boolean last;
    private List<Contents> contents;
    private Sort sort;

    @Getter
    @Setter
    public static class Sort {
        private boolean sorted;
        private List<Fields> fields;

        @Getter
        @Setter
        public static class Fields{
            private String name;
            private String direction;
        }
    }

    @Getter
    @Setter
    public static class Contents {
        private long id;
        private String wholeCategoryName;
        private String categoryId;
        private long manufacturerCode;
        private String manufacturerName;
        private long brandCode;
        private String brandName;
        private String name;
    }
}
