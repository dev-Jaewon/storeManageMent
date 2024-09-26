package com.won.StoreManageMent.naver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.won.StoreManageMent.configuration.SecurityConfig;
import com.won.StoreManageMent.naver.controller.NaverController;
import com.won.StoreManageMent.naver.dto.RequestNaverSellerInfo;
import com.won.StoreManageMent.naver.service.NaverApiService;
import com.won.StoreManageMent.naver.service.NaverInfoManageService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NaverController.class)
@Import(SecurityConfig.class)
public class NaverApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NaverApiService naverApiService;

    @MockBean
    private NaverInfoManageService naverInfoManageService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testInsertNaverSellerInfoBadRequest() throws Exception {

        RequestNaverSellerInfo NullNaverSellerInfo = RequestNaverSellerInfo.builder().build();

        Mockito.when(naverInfoManageService.addNaverSellerInfo(Mockito.any(RequestNaverSellerInfo.class))).thenThrow();

        mockMvc.perform(post("/naver/sellerInfo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(NullNaverSellerInfo)))
                .andExpect(status().isBadRequest());

        RequestNaverSellerInfo requestNaverSellerInfo = NaverRequestParam.getRequestNaverSellerInfo();

        Mockito.when(naverInfoManageService.addNaverSellerInfo(Mockito.any(RequestNaverSellerInfo.class))).thenReturn("성공적으로 등록되었습니다.");

        mockMvc.perform(post("/naver/sellerInfo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestNaverSellerInfo)))
                .andExpect(status().isCreated());
    }
}
