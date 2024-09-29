package com.won.StoreManageMent.naver.dto;

import com.won.StoreManageMent.naver.vo.NaverTagsVo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseTags {
    private List<NaverTagsVo> tags;
}
