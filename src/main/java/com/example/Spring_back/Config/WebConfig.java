package com.example.Spring_back.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 모든 경로에 대해 CORS 설정 적용
                .allowedOriginPatterns("http://localhost:5173") // Vue 개발 서버 주소
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 허용할 HTTP 메서드
                .allowedHeaders("*") // 모든 헤더 허용
                .allowCredentials(true) // 쿠키나 인증 헤더를 허용할 경우 필수
                .maxAge(3600); // 프리플라이트(Preflight) 요청 결과 캐싱 시간 (초)
    }
}