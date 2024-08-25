package com.won.StoreManageMent.coupang.service;

import com.won.StoreManageMent.coupang.dto.CoupangCategoryDto;

public interface CoupangMetaDataService {
    public CoupangCategoryDto.CategoryData getCategoryData(String displayCategoryCode);
}
