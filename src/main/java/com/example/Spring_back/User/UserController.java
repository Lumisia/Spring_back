package com.example.Spring_back.User;

import com.example.Spring_back.User.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(
        origins = "http://localhost:5173",
        allowCredentials = "true"
)
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserDto.SignupReq dto) {

        userService.signup(dto);
        return ResponseEntity.ok("성공");
    }

    @GetMapping("/verify")
    public ResponseEntity verify(String uuid) {

        userService.verify(uuid);

        return ResponseEntity.ok("성공");
    }
}
