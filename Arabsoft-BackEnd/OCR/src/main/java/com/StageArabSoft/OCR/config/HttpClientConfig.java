package com.StageArabSoft.OCR.config;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Configuration
@Component
public class HttpClientConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        CloseableHttpClient httpClient = HttpClients.custom()
                .build();

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
        return builder
                .requestFactory(() -> factory)
                .build();
    }
}
