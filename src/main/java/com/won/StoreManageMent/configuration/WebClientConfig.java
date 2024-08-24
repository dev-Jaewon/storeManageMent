package com.won.StoreManageMent.configuration;

import java.net.http.HttpClient;
import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebClientConfig {
    
    @Bean
    public HttpClient httpClient(){
        return HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(30)).build();
    }

}
