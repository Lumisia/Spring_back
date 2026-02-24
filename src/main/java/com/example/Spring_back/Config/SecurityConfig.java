package com.example.Spring_back.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationConfiguration configuration;
    private final LoginFilter loginFilter;
    private final JwtFilter jwtFilter;

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.setAllowedOrigins(List.of("http://localhost:5173"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // 모든 경로에 대해 CORS 적용
        return source;
    }

    @Bean // 개발자가 직접 개발한 코드가 아닌 클래스의 객체를 스프링의 빈으로 등록하려고 할 때 사용
    public SecurityFilterChain config(HttpSecurity http) throws Exception {
        // 특정 URI로 접속했을 때 권한 설정하는 부분
        // .permitAll() 전부 허용
        // .hasRole("ADMIN") AuthUserDetails 객체에서 ROLE_ADMIN 권한을 가진 사용자만 허용
        // .authenticated() 는 로그인 한 사용자만 허용
        http.authorizeHttpRequests(
                (auth) -> auth
                        .requestMatchers("/user/**", "/error").permitAll()
                        .anyRequest().authenticated()
        );

        // CSRF 방어 기능을 중지하는 코드
        http.csrf(AbstractHttpConfigurer::disable);
        // basic 로그인 방식 사용 안하도록 설정
        http.httpBasic(AbstractHttpConfigurer::disable);
        // form 로그인 방식 사용 안하도록 설정
        http.formLogin(AbstractHttpConfigurer::disable);

        // 기존 필터 대신에 내가 구현한 필터로 교체
        http.addFilterAt(loginFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
