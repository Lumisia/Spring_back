package com.example.Spring_back.Config;

import com.example.Spring_back.User.model.AuthUserDetails;
import com.example.Spring_back.Utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getServletPath();
        System.out.println(path);

        return path.startsWith("/login") ||
                path.startsWith("/user/signup") ||
                path.startsWith("/user/verify");

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("ATOKEN".equals(cookie.getName())) {
                    String token = cookie.getValue();

                    // 🔥 토큰이 비어있지 않고 유효한지 검증하는 로직 추가 필요
                    if (token != null && !token.isEmpty()) {
                        try {
                            Long idx = jwtUtil.getUserIdx(token);
                            String email = jwtUtil.getEmail(token);
                            String role = jwtUtil.getRole(token);

                            AuthUserDetails user =AuthUserDetails.builder()
                                    .idx(idx)
                                    .email(email)
                                    .role(role)
                                    .build();

                            if (user != null && role != null) {
                                Authentication authentication = new UsernamePasswordAuthenticationToken(
                                        user,
                                        null,
                                        List.of(new SimpleGrantedAuthority(role))
                                );
                                SecurityContextHolder.getContext().setAuthentication(authentication);
                            }
                        } catch (Exception e) {
                            // 토큰 파싱 중 에러 발생 시 로그 출력 (만료 등)
                            logger.error("JWT 검증 실패: " + e.getMessage());
                        }
                    }
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}

