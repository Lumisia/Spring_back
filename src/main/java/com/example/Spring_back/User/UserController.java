package com.example.Spring_back.User;

import com.example.Spring_back.User.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/signup")
    public ResponseEntity signup(@RequestBody UserDto.SignupReq dto) {

        userService.signup(dto);
        return ResponseEntity.ok("성공");

    }
}
