package com.example.Spring_back.User.model;

import lombok.Builder;
import lombok.Getter;

public class UserDto {

    @Getter
    public static class SignupReq {
        private String email;
        private String user_id;
        private String password;

        public User toEntity() {
            return User.builder()
                    .email(this.email)
                    .user_id(this.user_id)
                    .password(this.password)
                    .role("ROLE_USER")
                    .build();
        }
    }

    @Getter
    public static class LoginReq {
        private String email;
        private String password;
    }

    @Builder
    @Getter
    public static class LoginRes {
        private Long idx;
        private String email;
        private String user_id;

        public static LoginRes form(User entity) {
            return LoginRes.builder()
                    .idx(entity.getIdx())
                    .email(entity.getEmail())
                    .user_id(entity.getUser_id())
                    .build();
        }
    }
}
